package project.colon.fastdrive.data.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Fare {

    private String currency;
    private String text;
    private double value;
}
