package side_job.app.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import side_job.app.utilities.Enums.JobStatus;

@Entity
@Table(name="jobs")
@Getter
@Setter
public class Job {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String type;

    @Column(nullable=false)
    private String payload;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private JobStatus jobStatus;

    @Column(nullable=false)
    private String lastError;

    @Column(nullable=false)
    private int retryCount;

    @Column(nullable=false)
    private Instant runAt;

    @Column(nullable=false)
    private Instant createdAt;


    @Column(nullable=false)
    private Instant updatedAt;
    
    public Job(String type, String payload, Instant runAt ) {
        this.type = type;
        this.payload = payload;
        this.runAt = runAt;
        this.jobStatus = JobStatus.PENDING;
        this.retryCount = 0;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() { 
        this.updatedAt = Instant.now();
    }


}
