package project.colon.fastdrive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class FastDriveApplication {

    public static void main(String[] args) {
//        String port = System.getenv("${PORT}");
        SpringApplication.run(FastDriveApplication.class, args);
//        log.info("Server is starting @ port {} >>>>>> ", port);
    }

}
