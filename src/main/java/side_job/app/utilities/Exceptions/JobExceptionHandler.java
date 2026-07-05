package side_job.app.utilities.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JobExceptionHandler {
    
    @ExceptionHandler(EmailValidationFailException.class)
    public ResponseEntity<Map<String, String>> handleEmailException(EmailValidationFailException ex) { 
        Map<String, String> response = new HashMap<>();
        response.put("error", "EMAIL_VALIDATION_FAILED");
        response.put("message", ex.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotificationValidationFailException.class)
    public ResponseEntity<Map<String, String>> handleNotificationException(NotificationValidationFailException ex) { 
        Map<String, String> response = new HashMap<>();
        response.put("error", "NOTIFICATION_VALIDATION_FAILED");
        response.put("message", ex.getMessage());

        return ResponseEntity.badRequest().body(response);
    }
}
