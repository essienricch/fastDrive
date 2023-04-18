package project.colon.fastdrive.data.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterPassengerResponse {

    private Long id;
    private String message;
    private boolean isSuccess;
}
