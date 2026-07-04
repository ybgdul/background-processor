package side_job.app.utilities.Exceptions;

public class EmailValidationFailException extends RuntimeException{
    public EmailValidationFailException(String message) { 
        super(message);
    }
}
