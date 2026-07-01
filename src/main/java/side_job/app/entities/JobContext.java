package side_job.app.entities;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JobContext {
    
    private final Long jobId;
    private final int attempt;
    private final Instant startedAt;


}
