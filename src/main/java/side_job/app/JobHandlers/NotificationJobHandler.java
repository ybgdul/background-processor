package side_job.app.JobHandlers;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import side_job.app.entities.JobContext;
import side_job.app.services.NotificationJobService;
import side_job.app.utilities.DTOs.NotificationJobPayload;
import side_job.app.utilities.Interfaces.JobHandler;

@Component
@RequiredArgsConstructor
public class NotificationJobHandler implements JobHandler<NotificationJobPayload>{
    
    private final NotificationJobService notificationService;

    @Override
    public String getType() {
        return "notification";
    }

    @Override
    public Class<NotificationJobPayload> payloadType() {
        return NotificationJobPayload.class;
    }

    @Overridedsad
    public void handle(JobContext context, NotificationJobPayload payload) throws Exception {
        
    }

    
}
