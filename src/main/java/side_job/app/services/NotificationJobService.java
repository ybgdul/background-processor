package side_job.app.services;

import java.net.http.HttpHeaders;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationJobService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendPush(Long id, String fcmToken, String title, String body) { 

        String url = "https://fcm.googleapis.com/v1/projects/YOUR_PROJECT_ID/messages:send";

        String payload = """
        {
          "message": {
            "token": "%s",
            "notification": {
              "title": "%s",
              "body": "%s"
            }
          }
        }
        """.formatted(fcmToken, title, body);


        restTemplate.postForEntity(url, payload, String.class);
    }

    private String getAccessToken() { 
        return "some token";
    }
}
