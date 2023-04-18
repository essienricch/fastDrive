package project.colon.fastdrive.data.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterDriverResponse {

    private Long id;

    private String  message;

    private boolean isSuccess;
}
