package project.colon.fastdrive.service.passenger;

import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.data.domain.Page;
import project.colon.fastdrive.data.dto.request.BookRideRequest;
import project.colon.fastdrive.data.dto.request.RegisterPassengerRequest;
import project.colon.fastdrive.data.dto.response.ApiResponse;
import project.colon.fastdrive.data.dto.response.RegisterPassengerResponse;
import project.colon.fastdrive.data.model.Passenger;

import java.util.Optional;

public interface PassengerService {

    RegisterPassengerResponse registerPassenger(RegisterPassengerRequest passengerRequest);
    Passenger getPassengerById(Long passengerId);

    void savePassenger(Passenger passenger);
    Optional <Passenger> getPassengerBy(Long passengerId);
    Page <Passenger> getAllPassenger(int pageNumber);
    Passenger updatePassenger(Long passengerId, JsonPatch updatePayload);
    void deletePassenger(Long passengerId);


}
