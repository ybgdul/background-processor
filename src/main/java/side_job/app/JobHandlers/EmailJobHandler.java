package side_job.app.JobHandlers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import side_job.app.entities.JobContext;
import side_job.app.services.EmailJobService;
import side_job.app.utilities.DTOs.EmailJobPayload;
import side_job.app.utilities.Interfaces.JobHandler;

@Component
@RequiredArgsConstructor
public class EmailJobHandler implements JobHandler<EmailJobPayload> {

    private final EmailJobService emailService;

    @Override
    public Class<EmailJobPayload> payloadType() {
        return EmailJobPayload.class;
    }

    @Override
    public void handle(JobContext context, EmailJobPayload payload) throws Exception {
        emailService.send(payload.to(), payload.subject(), payload.body());
    }

    @Override
    public String getType() {
        return "email";
    }
    
}
