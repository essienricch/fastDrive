package project.colon.fastdrive.mapper;

import project.colon.fastdrive.data.dto.request.RegisterPassengerRequest;
import project.colon.fastdrive.data.model.AppUser;

public class ParaMapper {

    public static AppUser mapToAppUser(RegisterPassengerRequest registerPassengerRequest){
        AppUser user = new AppUser();
        user.setName(registerPassengerRequest.getUserName());
        user.setEmail(registerPassengerRequest.getEmailAddress());
        user.setPassword(registerPassengerRequest.getPassword());
        return user;
    }
}
