package project.colon.fastdrive.service.notification;

import project.colon.fastdrive.data.dto.request.EmailNotificationRequest;

public interface MailService {

    String sendHtmlMail(EmailNotificationRequest request);
}
