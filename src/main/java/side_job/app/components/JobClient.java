package side_job.app.components;

import java.time.Instant;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import side_job.app.entities.Job;
import side_job.app.queues.InMemoryJobQueue;

@Component
@RequiredArgsConstructor
public class JobClient {

    private final InMemoryJobQueue jobQueue;
    
    public void enqueue(String type, String payload) { 
        Job job = new Job(type, payload, Instant.now());

        jobQueue.submit(job);
    }
}
