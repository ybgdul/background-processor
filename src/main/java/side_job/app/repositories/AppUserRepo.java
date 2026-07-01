package side_job.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import side_job.app.entities.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, Long>{
    
    public Optional<AppUser> findByUsername(String username);

    public boolean existsByUsername(String username);
}
