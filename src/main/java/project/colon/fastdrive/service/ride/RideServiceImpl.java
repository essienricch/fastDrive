package project.colon.fastdrive.service.ride;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.colon.fastdrive.config.distance.DistanceConfig;
import project.colon.fastdrive.data.dto.request.BookRideRequest;
import project.colon.fastdrive.data.dto.request.Location;
import project.colon.fastdrive.data.dto.response.ApiResponse;
import project.colon.fastdrive.data.dto.response.DistanceMatrixElement;
import project.colon.fastdrive.data.dto.response.GoogleDistanceResponse;
import project.colon.fastdrive.data.dto.response.RideResponse;
import project.colon.fastdrive.data.model.Passenger;
import project.colon.fastdrive.data.model.Ride;
import project.colon.fastdrive.exception.BusinessLogicException;
import project.colon.fastdrive.service.passenger.PassengerService;
import project.colon.fastdrive.util.AppUtilities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
@Service
public class RideServiceImpl implements RideService{

    private final PassengerService passengerService;
    private final DistanceConfig directionConfig;


    @Override
    public Ride saveRide(Ride ride) {
        return null;
    }

    @Override
    public ApiResponse bookRide(BookRideRequest bookRideRequest) {
            //find passenger:
            Passenger foundPassenger = passengerService.getPassengerById(bookRideRequest.getPassengerId());
            if (foundPassenger == null) throw new BusinessLogicException(String.format("passenger with id %d not found", bookRideRequest.getPassengerId()));
            //calculate distance between origin and destination:
            DistanceMatrixElement distanceInfo = getDistanceInformation(bookRideRequest.getOrigin(), bookRideRequest.getDestination());
            //calculate Estimated Time of Arrival:
            String eta = distanceInfo.getDistance().getText();
            //calculate price:
            BigDecimal fare =  AppUtilities.calculateRideFare(distanceInfo.getDistance().getText());
            return ApiResponse.builder()
                    .fare(fare)
                    .estimatedTimeOfArrival(eta)
                    .build();
        }

        private DistanceMatrixElement getDistanceInformation(Location origin, Location destination) {
            RestTemplate restTemplate =new RestTemplate();
            String url = buildDistanceRequestUrl(origin, destination);
            ResponseEntity<GoogleDistanceResponse> response =
                    restTemplate.getForEntity(url, GoogleDistanceResponse.class);
            return Objects.requireNonNull(response.getBody()).getRows().stream()
                    .findFirst().orElseThrow()
                    .getElements().stream()
                    .findFirst()
                    .orElseThrow();
        }

        private String buildDistanceRequestUrl(Location origin, Location destination) {
            return directionConfig.getGoogleDistanceUrl()+"/"+ AppUtilities.JSON_CONSTANT+"?"+
                    "destinations="+AppUtilities.buildLocation(destination)+"&origins="+
                    AppUtilities.buildLocation(origin)+"&mode=driving"+"&traffic_model=pessimistic"
                    +"&departure_time="+ LocalDateTime.now().toEpochSecond(ZoneOffset.of("+01:00"))
                    +"&key="+directionConfig.getGoogleApiKey();
        }


    @Override
    public Page<Ride> getAllRide(int number) {
        return null;
    }

    @Override
    public RideResponse getRideById(Long rideId) {
        return null;
    }
}
