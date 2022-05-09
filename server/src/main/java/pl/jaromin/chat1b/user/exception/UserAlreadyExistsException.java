package pl.jaromin.chat1b.user.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String username) {
        super("User already exist: " + username);
    }
}
