package project.colon.fastdrive.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.colon.fastdrive.data.model.BankInformation;

public interface BankInformationRepository extends JpaRepository <BankInformation, Long> {
}
