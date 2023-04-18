package project.colon.fastdrive.service.ride;

import org.springframework.data.domain.Page;
import project.colon.fastdrive.data.dto.request.BookRideRequest;
import project.colon.fastdrive.data.dto.response.ApiResponse;
import project.colon.fastdrive.data.dto.response.RideResponse;
import project.colon.fastdrive.data.model.Ride;

import java.awt.print.Pageable;

public interface RideService {

    Ride saveRide(Ride ride);

    ApiResponse bookRide(BookRideRequest bookRideRequest);

    Page <Ride> getAllRide(int number);

    RideResponse getRideById(Long rideId);
}
