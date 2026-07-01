package side_job.app.utilities.Interfaces;

import side_job.app.entities.JobContext;

public interface JobHandler<T> {
    String getType();
    Class<T> payloadType();
    void handle(JobContext context, T payload) throws Exception;
}
