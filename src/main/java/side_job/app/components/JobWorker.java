package side_job.app.components;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import side_job.app.entities.Job;
import side_job.app.repositories.JobRepo;
import side_job.app.utilities.Enums.JobStatus;
import side_job.app.utilities.Exceptions.EmailValidationFailException;
import side_job.app.utilities.Exceptions.NotificationValidationFailException;
import side_job.app.utilities.Interfaces.JobQueue;
import side_job.app.utilities.Interfaces.RethrowFailure;
import side_job.app.utilities.POJOs.MaxRetries;
import side_job.app.utilities.POJOs.RetryPolicy;

@Component
@RequiredArgsConstructor
public class JobWorker implements Runnable {

    private final JobQueue jobQueue;
    private final JobMatcher jobMatcher;
    private final JobRepo jobRepo;
    private Logger log = LoggerFactory.getLogger(JobWorker.class);

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) { 
            try { 
                Job job = jobQueue.take();
                process(job);

            } catch( InterruptedException e) { 
                Thread.currentThread().interrupt();
            }
        }
    }

    private void process(Job job) { 
        try {
            job.setJobStatus(JobStatus.PROCESSING);
            job.setProcessingAt(Instant.now());
            jobRepo.save(job);

            jobMatcher.dispatch(job, job.getRetryCount());

            job.setJobStatus(JobStatus.SUCCESS);
            jobRepo.save(job);
        } catch (Exception e) {
            handleFailure(job, e);
        }
    }

    private void handleFailure(Job job, Exception e) { 
        if(e instanceof RethrowFailure) throw (RuntimeException) e;
        int nextTry = job.getRetryCount() + 1;
        job.setRetryCount(nextTry);
        job.setLastError(e.getMessage());

        if(nextTry >= MaxRetries.MAX_RETRIES) {
            job.setJobStatus(JobStatus.FAILED);
            jobRepo.save(job);
            log.error("Current job failed: " + job.getId() + " " + job.getType() + " " + e.getMessage());
        } else { 
            job.setJobStatus(JobStatus.PENDING);
            job.setRunAt(RetryPolicy.nextRetryAt(nextTry));
            jobRepo.save(job);
            jobQueue.submit(job);
        }
    }
    
}
