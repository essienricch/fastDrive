package project.colon.fastdrive.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String origin;

    private String destination;
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    private RideStatus rideStatus;
}
