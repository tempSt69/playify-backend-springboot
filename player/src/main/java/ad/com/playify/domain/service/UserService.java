package ad.com.playify.domain.service;

import ad.com.playify.domain.entity.User;
import ad.com.playify.domain.port.in.UserServicePort;
import ad.com.playify.domain.port.out.AuthPort;
import ad.com.playify.domain.port.out.UserPersistencePort;

public class UserService implements UserServicePort {

    private UserPersistencePort userPersistencePort;
    private AuthPort authPort;

    public UserService(UserPersistencePort userPersistencePort, AuthPort authPort) {
        this.userPersistencePort = userPersistencePort;
        this.authPort = authPort;
    }

    @Override
    public User register(User user) {
        String passwordEncoded = authPort.encodePassword(user.getPassword());
        user.setPassword(passwordEncoded);
        return userPersistencePort.createUser(user);
    }

    @Override
    public String login(User user) {
        User userFound = userPersistencePort.getUserByUsername(user.getUsername());
        if (userFound == null) {
            throw new RuntimeException("User not found");
        }
        if (!authPort.verifyPassword(user.getPassword(), userFound.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        return authPort.generateToken(userFound.getId());
    }
}
