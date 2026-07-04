package side_job.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import side_job.app.components.JobClient;
import side_job.app.utilities.DTOs.EmailJobPayload;
import side_job.app.utilities.Exceptions.EmailValidationFailException;

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
            jobClient.enqueue("email", objectMapper.writeValueAsString(emailRequest));
        } catch(JsonProcessingException e) { 
            throw new EmailValidationFailException("Serialization Failed");
        }

        return ResponseEntity.ok("The request has been processed and the email is sent");
    }

}
