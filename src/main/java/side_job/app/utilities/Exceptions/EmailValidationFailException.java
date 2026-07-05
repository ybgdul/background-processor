package side_job.app.utilities.Exceptions;

import side_job.app.utilities.Interfaces.RethrowFailure;

public class EmailValidationFailException extends RuntimeException implements RethrowFailure{
    public EmailValidationFailException(String message) { 
        super(message);
    }
}
