package side_job.app.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import side_job.app.entities.Job;
import side_job.app.utilities.Interfaces.JobQueue;

@Component
@RequiredArgsConstructor
public class JobWorker implements Runnable {

    private final JobQueue jobQueue;
    private final JobMatcher jobMatcher;
    private Logger log = LoggerFactory.getLogger(JobWorker.class);

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) { 
            try { 
                Job job = jobQueue.take();
                jobMatcher.dispatch(job, 0);

            } catch( InterruptedException e) { 
                Thread.currentThread().interrupt();
            } catch(Exception e ) { 
                log.error("Non-interruption kind of error in worker threads: " + e.getMessage());
            }
        }
    }
    
}
