package project.colon.fastdrive.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.colon.fastdrive.data.model.Referee;

public interface RefereeRepository extends JpaRepository <Referee, Long> {
}
