package project.colon.fastdrive.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.colon.fastdrive.data.model.Address;

public interface AddressRepository extends JpaRepository <Address, Long> {
}
