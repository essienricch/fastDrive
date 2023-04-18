package project.colon.fastdrive.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.colon.fastdrive.data.dto.request.EmailNotificationRequest;
import project.colon.fastdrive.data.dto.request.Recipient;
import project.colon.fastdrive.service.notification.MailService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class MailServiceImplTest {
    @Autowired
    private MailService mailService;

    private EmailNotificationRequest notificationRequest;

    @BeforeEach
    void setUp(){
        List < Recipient> recipientList = List.of(
                new Recipient("Da-vinchi", "henrynwali7@gmail.com")
        );
        notificationRequest = new EmailNotificationRequest();
        notificationRequest.setTo(recipientList);
        notificationRequest.setHtmlContent("Hello Mr Da-vinchi");

    }

    @Test
    void sendHtmlMail(){
        var response = mailService.sendHtmlMail(notificationRequest);
        assertThat(response).isNotNull();
    }

}