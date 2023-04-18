package project.colon.fastdrive.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.colon.fastdrive.data.model.Review;

public interface ReviewRepository extends JpaRepository <Review, Long> {
}
