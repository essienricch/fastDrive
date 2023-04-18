package project.colon.fastdrive.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.colon.fastdrive.data.dto.request.RegisterDriverRequest;
import project.colon.fastdrive.data.dto.response.ApiResponse;
import project.colon.fastdrive.exception.BusinessLogicException;
import project.colon.fastdrive.service.driver.DriverService;

@RestController
@RequestMapping("/api/v1/driver")
@AllArgsConstructor
public class DriverController {

    private final DriverService driverService;


    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity <?> register(@Valid @ModelAttribute RegisterDriverRequest driverRequest){
        try{
            var response = driverService.register(driverRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (BusinessLogicException exception){
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message(exception.getMessage()).build());

        }
    }


}
