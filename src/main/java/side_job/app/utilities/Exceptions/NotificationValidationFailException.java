package side_job.app.utilities.Exceptions;

import side_job.app.utilities.Interfaces.RethrowFailure;

public class NotificationValidationFailException extends RuntimeException implements RethrowFailure{
    public NotificationValidationFailException(String message) { 
        super(message);
    }
}
