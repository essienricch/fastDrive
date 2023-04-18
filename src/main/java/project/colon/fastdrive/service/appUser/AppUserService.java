package project.colon.fastdrive.service.appUser;

import org.springframework.web.multipart.MultipartFile;
import project.colon.fastdrive.data.dto.response.ApiResponse;
import project.colon.fastdrive.data.model.AppUser;

public interface AppUserService {

    ApiResponse uploadProfileImage(MultipartFile profileImg, Long userId);
    ApiResponse verifyAccount(Long userId, String token);

    AppUser getByEmail(String email);
}
