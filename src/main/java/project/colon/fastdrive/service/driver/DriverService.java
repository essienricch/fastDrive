package project.colon.fastdrive.service.driver;

import project.colon.fastdrive.data.dto.request.RegisterDriverRequest;
import project.colon.fastdrive.data.dto.response.RegisterDriverResponse;
import project.colon.fastdrive.data.model.Driver;

import java.util.Optional;

public interface DriverService {

    RegisterDriverResponse register(RegisterDriverRequest driverRequest);

    Optional <Driver> getDriverBy(Long driverId);

    void saveDriver(Driver driver);
}
