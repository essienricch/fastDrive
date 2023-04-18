package project.colon.fastdrive.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.colon.fastdrive.data.dto.response.ApiResponse;
import project.colon.fastdrive.exception.BusinessLogicException;
import project.colon.fastdrive.service.appUser.AppUserService;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping(value = "/upload/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadProfileImage(@RequestParam(value = "file")MultipartFile file, @PathVariable Long userId){
        try{
            ApiResponse response = appUserService.uploadProfileImage(file, userId);
            return ResponseEntity.ok(response);
        }catch (BusinessLogicException e){
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .build());
        }
    }


    @PostMapping("/account/verify")
    public ResponseEntity <?> verifyAccount(@RequestParam Long userId, @RequestParam String token){
        try{
            var response = appUserService.verifyAccount(userId, token);
            return ResponseEntity.ok(response);
        }catch (BusinessLogicException e){
            return ResponseEntity.badRequest().body(ApiResponse.builder()
                    .message(e.getMessage())
                    .build());
        }
    }
}
