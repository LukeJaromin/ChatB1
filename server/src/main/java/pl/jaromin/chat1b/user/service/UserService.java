package pl.jaromin.chat1b.user.service;

public interface UserService {
    String saveUser(String username);

    boolean userExists(String username);
}
