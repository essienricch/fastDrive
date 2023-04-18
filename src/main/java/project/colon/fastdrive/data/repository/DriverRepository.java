package project.colon.fastdrive.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.colon.fastdrive.data.model.Driver;

public interface DriverRepository extends JpaRepository <Driver, Long> {
}
