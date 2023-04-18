package project.colon.fastdrive.service.appUser;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.colon.fastdrive.service.cloud.CloudService;
import project.colon.fastdrive.data.dto.response.ApiResponse;
import project.colon.fastdrive.data.model.AppUser;
import project.colon.fastdrive.data.model.Driver;
import project.colon.fastdrive.data.model.Passenger;
import project.colon.fastdrive.data.repository.AppUserRepository;
import project.colon.fastdrive.exception.BusinessLogicException;
import project.colon.fastdrive.exception.UserNotFoundException;
import project.colon.fastdrive.service.driver.DriverService;
import project.colon.fastdrive.service.passenger.PassengerService;
import project.colon.fastdrive.util.AppUtilities;

import java.util.Optional;

import static project.colon.fastdrive.exception.ExceptionMessage.USER_WITH_ID_NOT_FOUND;

@Service
@AllArgsConstructor
@Slf4j
public class AppUserServiceImpl implements AppUserService{

    private final PassengerService passengerService;

    private final DriverService driverService;
    private final CloudService cloudService;

    private final AppUserRepository appUserRepository;
    @Override
    public ApiResponse uploadProfileImage(MultipartFile profileImg, Long userId) {
        Optional <Driver> foundDriver = Optional.empty();
        Optional <Passenger> foundPassenger;
        foundPassenger = findPassenger(userId);
        if (foundPassenger.isEmpty()){
            foundDriver = findDriver(userId);
        }
        if (foundPassenger.isEmpty() && foundDriver.isEmpty()){
            throw new UserNotFoundException(
                    String.format(USER_WITH_ID_NOT_FOUND.getMessage(), userId)
            );
        }
            String imageUrl = cloudService.upload(profileImg);
            foundPassenger.ifPresent(passenger -> updatePassengerProfileImage(imageUrl, passenger));
            foundDriver.ifPresent(driver -> updateDriverProfileImage(imageUrl, driver));
        return ApiResponse.builder()
                .message("SUCCESS")
                .build();
    }

    private void updateDriverProfileImage(String imageUrl, Driver driver) {
        driver.getUser().setProfileImg(imageUrl);
        driverService.saveDriver(driver);
    }

    private void updatePassengerProfileImage(String imageUrl, Passenger passenger) {
        passenger.getUser().setProfileImg(imageUrl);
        passengerService.savePassenger(passenger);
    }


    private Optional<Driver> findDriver(Long userId) {
        return driverService.getDriverBy(userId);
    }

    private Optional<Passenger> findPassenger(Long userId) {
        return passengerService.getPassengerBy(userId);
    }

    @Override
    public ApiResponse verifyAccount(Long userId, String token) {
        if (AppUtilities.isValidToken(token))
        return getVerifiedResponse(userId);
        else throw new BusinessLogicException(String.format("account verification for user with %d failed", userId));
    }

    private ApiResponse getVerifiedResponse(Long userId) {
        Optional <Passenger> foundPassenger;
        Optional<Driver> foundDriver = Optional.empty();

        foundPassenger = findPassenger(userId);
        if (foundPassenger.isEmpty()){
            foundDriver = findDriver(userId);
        }
        if (foundDriver.isEmpty() && foundPassenger.isEmpty()){
            throw new UserNotFoundException(
                    String.format(USER_WITH_ID_NOT_FOUND.getMessage(), userId)
            );
        }
            foundDriver.ifPresent(driver -> enableDriverAccount(driver));
            foundPassenger.ifPresent(this::enablePassengerAccount);
        return ApiResponse.builder()
                .message("success")
                .build();
    }

    private void enablePassengerAccount(Passenger passenger) {
        passenger.getUser().setIsEnabled(true);
        passengerService.savePassenger(passenger);
    }

    private void enableDriverAccount(Driver driver) {
        driver.getUser().setIsEnabled(true);
        driverService.saveDriver(driver);
    }

    @Override
    public AppUser getByEmail(String email) {
        return appUserRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(String.format("User with email %s not found", email)));
    }
}
