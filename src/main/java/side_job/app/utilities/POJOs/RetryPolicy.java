package side_job.app.utilities.POJOs;

import java.time.Instant;

public class RetryPolicy {
    
    public static Instant nextRetryAt(int retryCount) { 
        long wait = (long) Math.pow(2, retryCount);
        return Instant.now().plusSeconds(wait);
    }
}
