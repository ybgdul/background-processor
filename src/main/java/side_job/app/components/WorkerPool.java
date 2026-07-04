package side_job.app.components;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import side_job.app.repositories.JobRepo;
import side_job.app.utilities.Interfaces.JobQueue;

@Component
@RequiredArgsConstructor
public class WorkerPool {
    
    private final JobQueue jobQueue;
    private final JobMatcher jobMatcher;
    private final JobRepo jobRepo;

    private final int workerCount = 5;

    @PostConstruct
    public void startWorkers() { 
        ExecutorService workers = Executors.newFixedThreadPool(5);
        for(int i = 0; i < workerCount; i++) { 
            workers.submit(new JobWorker(jobQueue, jobMatcher, jobRepo));
        }
    }
}
