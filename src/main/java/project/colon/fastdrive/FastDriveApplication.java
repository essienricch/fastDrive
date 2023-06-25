package project.colon.fastdrive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
@Slf4j
public class FastDriveApplication {

    public static void main(String[] args) {

        Properties properties = new Properties();
        try{
            FileInputStream file = new FileInputStream("C:\\Users\\New Owner\\IdeaProjects\\fastDrive\\src\\main\\resources\\application.properties");
            properties.load(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String port = properties.getProperty("server.port");
        SpringApplication.run(FastDriveApplication.class, args);
        log.info("Server is starting @ port {} >>>>",port);
    }

}
