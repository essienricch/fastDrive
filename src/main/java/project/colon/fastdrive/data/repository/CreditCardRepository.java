package project.colon.fastdrive.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.colon.fastdrive.data.model.CreditCard;

public interface CreditCardRepository extends JpaRepository <CreditCard, Long> {
}
