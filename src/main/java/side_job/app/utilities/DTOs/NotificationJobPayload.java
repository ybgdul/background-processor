package side_job.app.utilities.DTOs;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;

public record NotificationJobPayload(@NotBlank Long id, @NotBlank String fcmToken, @NotBlank String title, String body, @NotBlank Instant runAt) {
    
}
