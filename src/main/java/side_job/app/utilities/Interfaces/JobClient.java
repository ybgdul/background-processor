package side_job.app.utilities.Interfaces;

public interface JobClient {
    
    <T> void submit(String type, T payload);
}
