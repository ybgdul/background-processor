package side_job.app.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.mail.SendFailedException;
import lombok.RequiredArgsConstructor;
import side_job.app.repositories.AppUserRepo;

@Service
@RequiredArgsConstructor
public class EmailJobService {

    private final JavaMailSender mailSender;
    private final AppUserRepo userRepo;
    
    public void send(String to, String subject, String body ) throws SendFailedException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String email = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found by username: " + username)).getEmail();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(email);
        msg.setSubject(subject);
        msg.setText(body);
        mailSender.send(msg);
    }
}
