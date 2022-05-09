package pl.jaromin.chat1b.user.service;

import pl.jaromin.chat1b.user.exception.UserAlreadyExistsException;
import pl.jaromin.chat1b.user.exception.UserDoesNotExistException;
import pl.jaromin.chat1b.user.entity.ChatUser;
import pl.jaromin.chat1b.user.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Transactional
@Singleton
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Override
    public String saveUser(String username) {
        if (userRepository.isPresent(username)) {
            throw new UserAlreadyExistsException(username);
        }
        ChatUser chatUser = new ChatUser();
        chatUser.setName(username);
        userRepository.save(chatUser);
        return "User: " + username + " added successfully.";
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.isPresent(username);
    }
}
