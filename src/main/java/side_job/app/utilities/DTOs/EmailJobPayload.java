package side_job.app.utilities.DTOs;

public record EmailJobPayload (
    String to,
    String subject,
    String body
) {
    
}
