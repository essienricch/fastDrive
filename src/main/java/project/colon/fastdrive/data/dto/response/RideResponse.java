package project.colon.fastdrive.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.colon.fastdrive.data.dto.request.Location;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RideResponse {
    private String driverName;
    private String passengerName;

    private Location origin;

    private Location destination;
}
