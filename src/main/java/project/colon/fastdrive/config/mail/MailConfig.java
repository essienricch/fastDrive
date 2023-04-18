package project.colon.fastdrive.config.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MailConfig {

    private String apiKey;
    private String mailUrl;
}
