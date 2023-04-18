package project.colon.fastdrive.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.colon.fastdrive.data.model.Admin;

public interface AdminRepository extends JpaRepository <Admin, Long> {
}
