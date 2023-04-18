package project.colon.fastdrive.exception;

public class UserNotFoundException extends BusinessLogicException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
