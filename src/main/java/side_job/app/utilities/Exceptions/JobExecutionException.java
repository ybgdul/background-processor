package side_job.app.utilities.Exceptions;

public class JobExecutionException extends RuntimeException {

    private final boolean retryable;

    public JobExecutionException(String message, boolean retryable) { 
        super(message);
        this.retryable = retryable;
    }

    public JobExecutionException(String message, Throwable cause, boolean retryable) { 
        super(message, cause);
        this.retryable = retryable;
    }

    public boolean isRetryable() {
        return retryable;
    }
    
}
