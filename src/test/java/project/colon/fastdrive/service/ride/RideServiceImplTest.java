package project.colon.fastdrive.service.ride;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.colon.fastdrive.data.dto.request.BookRideRequest;
import project.colon.fastdrive.data.dto.request.Location;
import project.colon.fastdrive.data.dto.request.RegisterPassengerRequest;
import project.colon.fastdrive.data.dto.response.ApiResponse;
import project.colon.fastdrive.data.dto.response.RegisterPassengerResponse;
import project.colon.fastdrive.service.passenger.PassengerService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class RideServiceImplTest {

    @Autowired
    private RideService rideService;

    @Autowired
    private PassengerService passengerService;
    private RegisterPassengerRequest registerPassengerRequest;

    @BeforeEach
    void setUp(){
        registerPassengerRequest = new RegisterPassengerRequest();
        registerPassengerRequest.setUserName("NancyIsime");
        registerPassengerRequest.setEmailAddress("nancy2022@gmail.com");
        registerPassengerRequest.setPassword("2023");
    }

    @Test
    void bookRideTest(){
        var newPassenger = passengerService.registerPassenger(registerPassengerRequest);
        var savedPassenger = passengerService.getPassengerById(newPassenger.getId());
        BookRideRequest bookRideRequest = buildRideRequest(savedPassenger.getId());
        ApiResponse bookRideResponse = rideService.bookRide(bookRideRequest);
        log.info("response -> {}", bookRideResponse);
        assertThat(bookRideResponse).isNotNull();
    }

    private BookRideRequest buildRideRequest(Long id) {
        BookRideRequest rideRequest = new BookRideRequest();
        rideRequest.setPassengerId(id);
        rideRequest.setOrigin(new Location("121","mushin road", "itire", "Lagos"));
        rideRequest.setDestination(new Location("131","mushin road", "itire","Lagos"));
        return rideRequest;
    }

}