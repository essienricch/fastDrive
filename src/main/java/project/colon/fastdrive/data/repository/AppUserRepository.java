package project.colon.fastdrive.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.colon.fastdrive.data.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository <AppUser , Long> {

    Optional <AppUser> findByEmail(String email);
}
