package side_job.app.queues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Service;

import side_job.app.entities.Job;
import side_job.app.utilities.Interfaces.JobQueue;

@Service
public class InMemoryJobQueue implements JobQueue{
    
    private final BlockingQueue<Job> queue = new LinkedBlockingQueue<>();

    @Override
    public void submit(Job job) { 
        queue.offer(job);
    }

    @Override
    public Job take() throws InterruptedException {
        return queue.take();
    }
}
