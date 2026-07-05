package side_job.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.validation.Valid;
import side_job.app.components.JobClient;
import side_job.app.utilities.DTOs.EmailJobPayload;
import side_job.app.utilities.DTOs.NotificationJobPayload;
import side_job.app.utilities.Exceptions.EmailValidationFailException;
import side_job.app.utilities.Exceptions.NotificationValidationFailException;

@RestController
@RequestMapping("/jobs")
public class JobController {
    
    private final JobClient jobClient;
    private final ObjectMapper objectMapper;

    JobController(JobClient jobClient, ObjectMapper objectMapper) {
        this.jobClient = jobClient;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailJobPayload emailRequest) { 
        try { 
            String payload = objectMapper.writeValueAsString(emailRequest);
            jobClient.enqueue("email", payload);
        } catch(JsonProcessingException e) { 
            throw new EmailValidationFailException("Serialization Failed");
        }

        return ResponseEntity.ok("The request has been processed and the email is sent");
    }

    @PostMapping("/notification")
    public ResponseEntity<String> sendNotification(@Valid @RequestBody NotificationJobPayload notificationRequest) { 
        try { 
            String payload = objectMapper.writeValueAsString(notificationRequest);
            jobClient.enqueueFuture("notification", payload, notificationRequest.runAt());
        } catch(JsonProcessingException e) { 
            throw new NotificationValidationFailException("Serialization Failed");
        }

        return ResponseEntity.ok("The request has been processed and the notification will be sent");
    }

}
