package project.colon.fastdrive.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.colon.fastdrive.data.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
