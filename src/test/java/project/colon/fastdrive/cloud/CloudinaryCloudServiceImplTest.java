package project.colon.fastdrive.cloud;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import project.colon.fastdrive.service.cloud.CloudService;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static project.colon.fastdrive.util.AppUtilities.FAST_DRIVE_TEST_IMAGE;

@SpringBootTest
@Slf4j
class CloudinaryCloudServiceImplTest {

    @Autowired
    private CloudService cloudService;

    private MockMultipartFile file;

    @BeforeEach
    void setUp() throws IOException {
        file = new MockMultipartFile("classy", new FileInputStream(FAST_DRIVE_TEST_IMAGE));
    }

    @Test
    void uploadTest(){
        var cloudinaryImageUrl = cloudService.upload(file);
        assertThat(cloudinaryImageUrl).isNotNull();
    }

}