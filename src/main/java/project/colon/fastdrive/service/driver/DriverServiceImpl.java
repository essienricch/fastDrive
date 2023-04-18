package project.colon.fastdrive.service.driver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.colon.fastdrive.service.cloud.CloudService;
import project.colon.fastdrive.data.dto.request.EmailNotificationRequest;
import project.colon.fastdrive.data.dto.request.Recipient;
import project.colon.fastdrive.data.dto.request.RegisterDriverRequest;
import project.colon.fastdrive.data.dto.response.RegisterDriverResponse;
import project.colon.fastdrive.data.model.AppUser;
import project.colon.fastdrive.data.model.Driver;
import project.colon.fastdrive.data.model.Role;
import project.colon.fastdrive.data.repository.DriverRepository;
import project.colon.fastdrive.exception.ImageUploadException;
import project.colon.fastdrive.service.notification.MailService;
import project.colon.fastdrive.util.AppUtilities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class DriverServiceImpl implements DriverService{

    private final ModelMapper modelMapper;

    private final CloudService cloudService;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    private final DriverRepository driverRepository;
    @Override
    public RegisterDriverResponse register(RegisterDriverRequest driverRequest) {
        AppUser driverDetails = modelMapper.map(driverRequest, AppUser.class);
        driverDetails.setPassword(passwordEncoder.encode(driverDetails.getPassword()));
        driverDetails.setRoles(new HashSet<>());
        driverDetails.getRoles().add(Role.DRIVER);
        driverDetails.setCreatedAt(LocalDateTime.now().toString());
        //upload drivers license image:
        var imageUrl = cloudService.upload(driverRequest.getLicenseImage());
        if (imageUrl == null)
            throw new ImageUploadException("Driver Registration failed");
        //create driver object:
        Driver driver = Driver.builder()
                .user(driverDetails)
                .licenseImage(imageUrl)
                .build();
        //save driver:
        Driver savedDriver = driverRepository.save(driver);
        //send verification mail to driver
        EmailNotificationRequest emailRequest = buildNotificationRequest(savedDriver.getUser().getEmail(),
                savedDriver.getUser().getName(), savedDriver.getId());
        String response = mailService.sendHtmlMail(emailRequest);
        if (response == null) return getRegisterFailureResponse();
        else return RegisterDriverResponse.builder()
                .id(savedDriver.getId())
                .isSuccess(true)
                .message("Driver Registration Successful")
                .build();
    }

    private static RegisterDriverResponse getRegisterFailureResponse(){
        return RegisterDriverResponse.builder()
                .id(-1L)
                .isSuccess(false)
                .message("Driver Registration Failed")
                .build();
    }

    private EmailNotificationRequest buildNotificationRequest(String email, String name, Long userId){
        EmailNotificationRequest request = new EmailNotificationRequest();
        request.getTo().add(new Recipient(name, email));
        String template = AppUtilities.getMailTemplate();
        String content = String.format(template, name, AppUtilities.generateVerificationLink(userId));
        request.setHtmlContent(content);
        return request;
    }

    @Override
    public Optional<Driver> getDriverBy(Long driverId) {
        return driverRepository.findById(driverId);
    }

    @Override
    public void saveDriver(Driver driver) {
        driverRepository.save(driver);
    }
}
