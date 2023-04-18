package project.colon.fastdrive.service.notification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.colon.fastdrive.config.mail.MailConfig;
import project.colon.fastdrive.data.dto.request.EmailNotificationRequest;

@Service
@AllArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService{

    private final MailConfig mailConfig;
    @Override
    public String sendHtmlMail(EmailNotificationRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("api-key", mailConfig.getApiKey());
        HttpEntity <EmailNotificationRequest> requestHttpEntity =
                new HttpEntity<>(request, httpHeaders);
        ResponseEntity <String> response = restTemplate.postForEntity(mailConfig.getMailUrl(), requestHttpEntity, String.class);
        log.info("res->{}", response);
        return response.getBody();
    }
}
