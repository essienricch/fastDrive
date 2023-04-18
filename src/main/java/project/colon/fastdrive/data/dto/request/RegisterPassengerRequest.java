package project.colon.fastdrive.data.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterPassengerRequest {

    private String emailAddress;
    private String password;
    private String userName;
}
