package side_job.app.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import side_job.app.entities.Job;

public interface JobRepo extends JpaRepository<Job, Long> {
    
    @Query(value = "SELECT j FROM Job j WHERE (j.status = 'PENDING' AND j.runAt <= :now) OR (j.status = 'PROCESSING' AND j.processingAt <= :timeout) ORDER BY j.runAt ASC")
    public List<Job> findDueJobs(@Param("now") Instant now, @Param("timeout") Instant timeout);
    
}
