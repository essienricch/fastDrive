package project.colon.fastdrive;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FastDriveApplicationTest.class)
@Slf4j
class FastDriveApplicationTest {

    @Test
    void contextLoads(){
    }

//    @Test
//    void dataConnectionTest(){
//
//        DataSource dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/fastdrive_db");
//
//        try {
//            Connection connection = dataSource.getConnection("${USERNAME}", "${PASSWORD}");
//            assertThat(connection).isNotNull();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }




}