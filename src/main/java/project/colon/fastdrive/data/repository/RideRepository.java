package project.colon.fastdrive.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.colon.fastdrive.data.model.Ride;

public interface RideRepository extends JpaRepository <Ride, Long> {
}
