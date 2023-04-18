package project.colon.fastdrive.service.driver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import project.colon.fastdrive.data.dto.request.RegisterDriverRequest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static project.colon.fastdrive.util.AppUtilities.FAST_DRIVE_TEST_IMAGE;

@SpringBootTest
class DriverServiceImplTest {

    @Autowired
    private DriverService driverService;

    private RegisterDriverRequest request;

    @BeforeEach
    void setUp(){
        request = new RegisterDriverRequest();
        request.setName("test");
        request.setEmail("rich@email.com");
        request.setPassword("test_password");
    }

    @Test
    void register() throws IOException {
        MockMultipartFile file =
                new MockMultipartFile("test_license",
                        new FileInputStream(FAST_DRIVE_TEST_IMAGE));
        request.setLicenseImage(file);
        var response = driverService.register(request);
        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isTrue();
    }

}