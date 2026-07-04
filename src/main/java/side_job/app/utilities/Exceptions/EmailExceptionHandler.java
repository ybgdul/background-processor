package side_job.app.utilities.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmailExceptionHandler {
    
    @ExceptionHandler(EmailValidationFailException.class)
    public ResponseEntity<Map<String, String>> handleEmailException(EmailValidationFailException ex) { 
        Map<String, String> response = new HashMap<>();
        response.put("error", "EMAIL_VALIDATION_FAILED");
        response.put("message", ex.getMessage());

        return ResponseEntity.badRequest().body(response);
    }
}
