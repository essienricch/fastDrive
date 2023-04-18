package project.colon.fastdrive.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.colon.fastdrive.data.dto.request.RegisterPassengerRequest;
import project.colon.fastdrive.data.dto.response.RegisterPassengerResponse;
import project.colon.fastdrive.data.model.Passenger;
import project.colon.fastdrive.service.passenger.PassengerService;

@RequestMapping("/api/v1/passenger")
@RestController
@AllArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping("/register")
    public ResponseEntity <?> register(@RequestBody RegisterPassengerRequest passengerRequest){
        RegisterPassengerResponse passengerResponse = passengerService.registerPassenger(passengerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(passengerResponse);
    }


    @GetMapping("/{passengerId}")
    public ResponseEntity <?> getPassengerById(@PathVariable Long passengerId){
       var passenger = passengerService.getPassengerById(passengerId);
        return ResponseEntity.status(HttpStatus.OK).body(passenger);
    }

    @GetMapping("/all/{pageNumber}")
    public ResponseEntity <?> getAllPassengers(@PathVariable int pageNumber){
        var response = passengerService.getAllPassenger(pageNumber);
        return  ResponseEntity.ok(response.getContent());
    }


    @PatchMapping(value = "{passengerId}", consumes = {"application/json-patch+json"})
    public ResponseEntity <?> updatePassenger(@PathVariable Long passengerId, @RequestBody JsonPatch updatePayload){
        try{
            var response = passengerService.updatePassenger(passengerId, updatePayload);
            return  ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{passengerId}")
    public ResponseEntity <?> deletePassenger(@PathVariable Long passengerId){
        passengerService.deletePassenger(passengerId);
        return ResponseEntity.ok("Passenger deleted successfully");
    }


}
