package side_job.app.utilities.Interfaces;

import side_job.app.entities.Job;

public interface JobHandler {
    
    void handle(Job job) throws Exception;

    String type();
}
