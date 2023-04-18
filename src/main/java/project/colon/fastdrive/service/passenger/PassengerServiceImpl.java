package project.colon.fastdrive.service.passenger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.colon.fastdrive.config.distance.DistanceConfig;
import project.colon.fastdrive.data.dto.request.BookRideRequest;
import project.colon.fastdrive.data.dto.request.Location;
import project.colon.fastdrive.data.dto.request.RegisterPassengerRequest;
import project.colon.fastdrive.data.dto.response.ApiResponse;
import project.colon.fastdrive.data.dto.response.DistanceMatrixElement;
import project.colon.fastdrive.data.dto.response.GoogleDistanceResponse;
import project.colon.fastdrive.data.dto.response.RegisterPassengerResponse;
import project.colon.fastdrive.data.model.AppUser;
import project.colon.fastdrive.data.model.Passenger;
import project.colon.fastdrive.data.model.Role;
import project.colon.fastdrive.data.repository.PassengerRepository;
import project.colon.fastdrive.exception.BusinessLogicException;
import project.colon.fastdrive.exception.PassengerDbException;
import project.colon.fastdrive.mapper.ParaMapper;
import project.colon.fastdrive.util.AppUtilities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

import static project.colon.fastdrive.util.AppUtilities.NUMBER_OF_ITEMS_PER_PAGE;

@Slf4j
@AllArgsConstructor
@Service
public class PassengerServiceImpl implements PassengerService{

    private final PassengerRepository passengerRepository;

    private final PasswordEncoder passwordEncoder;






    @Override
    public RegisterPassengerResponse registerPassenger(RegisterPassengerRequest passengerRequest) {
        AppUser user = ParaMapper.mapToAppUser(passengerRequest);
        user.setRoles(new HashSet<>());
        user.getRoles().add(Role.PASSENGER);
        user.setPassword(passwordEncoder.encode(passengerRequest.getPassword()));
        user.setCreatedAt(LocalDateTime.now().toString());
        Passenger passenger = new Passenger();
        passenger.setUser(user);
        var savedPassenger = passengerRepository.save(passenger);
            return RegisterPassengerResponse.builder()
                    .id(savedPassenger.getId())
                    .isSuccess(true)
                    .message("Passenger registered successfully")
                    .build();

    }

    @Override
    public Passenger getPassengerById(Long passengerId) {
        return passengerRepository.findById(passengerId)
                .orElseThrow(() -> new PassengerDbException(String.format("Passenger with id %d not found", passengerId)));
    }

    @Override
    public void savePassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @Override
    public Optional<Passenger> getPassengerBy(Long passengerId) {
        return passengerRepository.findById(passengerId);
    }

    @Override
    public Page<Passenger> getAllPassenger(int pageNumber) {
        if (pageNumber < 1) pageNumber = 0;
        else pageNumber = pageNumber - 1;
        Pageable pageable = PageRequest.of(pageNumber, NUMBER_OF_ITEMS_PER_PAGE);
        return passengerRepository.findAll(pageable);
    }

    @Override
    public Passenger updatePassenger(Long passengerId, JsonPatch updatePayload) {
        ObjectMapper mapper = new ObjectMapper();
        Passenger foundPassenger = getPassengerById(passengerId);
        AppUser passengerDetail = foundPassenger.getUser();
        //Convert Passenger to Node
        JsonNode node = mapper.convertValue(foundPassenger, JsonNode.class);
        try{
            //apply Patch
            JsonNode updatedNode = updatePayload.apply(node);
            //node to Passenger Object
            var updatedPassenger = mapper.convertValue(updatedNode, Passenger.class);
            return passengerRepository.save(updatedPassenger);
        } catch (JsonPatchException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deletePassenger(Long passengerId) {
        passengerRepository.deleteById(passengerId);
    }


}
