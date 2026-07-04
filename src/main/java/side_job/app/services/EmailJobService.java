package side_job.app.services;

import java.nio.file.AccessDeniedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import side_job.app.repositories.AppUserRepo;
import side_job.app.utilities.Exceptions.EmailValidationFailException;

@Service
@RequiredArgsConstructor
public class EmailJobService {

    private final JavaMailSender mailSender;
    private final AppUserRepo userRepo;
    private static Logger log = LoggerFactory.getLogger(EmailJobService.class);
    
    public void send(String to, String subject, String body ) throws AccessDeniedException, MessagingException {
        String email = validation(to, subject, body);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setTo(to);
        helper.setFrom(email);
        helper.setSubject(subject);
        helper.setText(body);
        mailSender.send(mimeMessage);
    }

    private String validation(String to, String subject, String body) throws AccessDeniedException { 
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null) {throw new AccessDeniedException("User not authenticated");}
        String username = auth.getName();
        String email = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found by username: " + username)).getEmail();
        if(to.equalsIgnoreCase(email)) throw new EmailValidationFailException("Cannot send an email to yourself");
        return email;
    }
}
