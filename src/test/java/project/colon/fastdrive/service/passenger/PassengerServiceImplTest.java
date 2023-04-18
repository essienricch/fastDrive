package project.colon.fastdrive.service.passenger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.ReplaceOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.colon.fastdrive.FastDriveApplication;
import project.colon.fastdrive.data.dto.request.BookRideRequest;
import project.colon.fastdrive.data.dto.request.Location;
import project.colon.fastdrive.data.dto.request.RegisterPassengerRequest;
import project.colon.fastdrive.data.dto.response.ApiResponse;
import project.colon.fastdrive.data.dto.response.RegisterPassengerResponse;
import project.colon.fastdrive.data.model.AppUser;
import project.colon.fastdrive.exception.PassengerDbException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = FastDriveApplication.class)
@Slf4j
class PassengerServiceImplTest {

    @Autowired
    private PassengerService passengerService;

    private  RegisterPassengerRequest passengerRequest;




    @BeforeEach
    void setUp(){
        passengerRequest = new RegisterPassengerRequest();
        passengerRequest.setEmailAddress("tobechukwu@yahoo.com");
        passengerRequest.setUserName("Praise");
        passengerRequest.setPassword("2023");
    }

    @Test
    void registerPassengerTest(){
        var passengerRegResponse = passengerService.registerPassenger(passengerRequest);
        System.out.println(passengerRegResponse.getId());
        assertThat(passengerRegResponse).isNotNull();
    }

    @Test
    void getPassengerById(){
        var passenger = passengerService.registerPassenger(passengerRequest);
        var foundPassenger = passengerService.getPassengerById(passenger.getId());
        assertThat(foundPassenger).isNotNull();
        AppUser user = foundPassenger.getUser();
        assertThat(user.getName()).isEqualTo(passengerRequest.getUserName());
    }

    @Test
    void deletePassengerByIdTest(){
        RegisterPassengerRequest passengerRequest1 = new RegisterPassengerRequest("savy@gmail.com", "2023","lolly");
        var passenger = passengerService.registerPassenger(passengerRequest1);
        passengerService.deletePassenger(passenger.getId());
        assertThat(passengerRequest).isNull();
//        assertThrows(PassengerDbException.class, () -> passengerService.getPassengerBy(passenger.getId()));
    }


    @Test
    void updatePassengerTest() throws JsonProcessingException, JsonPointerException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree("2349099906543");
        JsonPatch updatePayload = new JsonPatch(List.of
                (new ReplaceOperation(new JsonPointer("/phoneNumber"),
                        node)));
//        RegisterPassengerRequest passengerRequest1 = new RegisterPassengerRequest("savy@gmail.com", "2023","lolly");
        var registerResponse = passengerService.registerPassenger(passengerRequest);
        var updatedPassenger = passengerService.updatePassenger(1L, updatePayload);
        assertThat(updatedPassenger).isNotNull();
        assertThat(updatedPassenger.getPhoneNumber()).isNotNull();
    }



}