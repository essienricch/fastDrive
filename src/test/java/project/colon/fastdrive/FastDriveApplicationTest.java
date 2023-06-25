package project.colon.fastdrive;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FastDriveApplicationTest.class)
@Slf4j
class FastDriveApplicationTest {

    @Test
    void contextLoads(){
    }

    @Test
    void dataConnectionTest(){

        Properties properties = new Properties();

        try {
            FileInputStream file = new FileInputStream("C:\\Users\\New Owner\\IdeaProjects\\fastDrive\\src\\main\\resources\\secrets.properties");
            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String username = properties.getProperty("spring.datasource.username");
        log.info(username);
        String password = properties.getProperty("spring.datasource.password");
        log.info(password);

        DataSource dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/fastdrive_db");

        try {
            Connection connection = dataSource.getConnection(username, password);
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}