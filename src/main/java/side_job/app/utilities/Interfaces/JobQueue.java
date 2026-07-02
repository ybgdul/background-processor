package side_job.app.utilities.Interfaces;

import side_job.app.entities.Job;

public interface JobQueue {
    void submit(Job job);
    Job take( ) throws InterruptedException;
}
