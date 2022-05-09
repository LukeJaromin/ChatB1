package pl.jaromin.chat1b.user.exception;

public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException(String username) {
        super("User does not exist: " + username);
    }
}
