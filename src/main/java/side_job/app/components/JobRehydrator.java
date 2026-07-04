package side_job.app.components;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import side_job.app.entities.Job;
import side_job.app.repositories.JobRepo;
import side_job.app.utilities.Interfaces.JobQueue;

@Component
@RequiredArgsConstructor
public class JobRehydrator {
    
    private final JobQueue jobQueue;
    private final JobRepo jobRepo;

    @PostConstruct
    public void loadPendingJobs() { 
        Instant now = Instant.now();

        List<Job> jobs = jobRepo.findDueJobs(now, now.minusSeconds(60));

        for(Job job : jobs) { 
            jobQueue.submit(job);
        }
    }
}
