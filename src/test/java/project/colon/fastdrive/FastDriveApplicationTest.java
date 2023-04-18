package project.colon.fastdrive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FastDriveApplicationTest {

    @Test
    void contextLoads(){
    }

    @Test
    void dataConnectionTest(){
        DataSource dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/fastdrive_db",
                "postgres","momentum");

        try {
            Connection connection = dataSource.getConnection();
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}