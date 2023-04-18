package ad.com.playify.adapter.out.persistence.adapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ad.com.playify.adapter.out.persistence.entity.UserJpa;
import ad.com.playify.adapter.out.persistence.mapper.UserMapper;
import ad.com.playify.adapter.out.persistence.repository.UserRepository;
import ad.com.playify.domain.entity.User;
import ad.com.playify.domain.port.out.UserPersistencePort;

public class UserJpaAdapter implements UserPersistencePort {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        Optional<UserJpa> userJpa = userRepository.findByUsername(username);

        if (userJpa.isPresent()) {
            return UserMapper.INSTANCE.userJpaToUser(userJpa.get());
        }

        return null;
    }

    @Override
    public User createUser(User user) {
        UserJpa createdUser = UserMapper.INSTANCE.userToUserJpa(user);

        return UserMapper.INSTANCE.userJpaToUser(createdUser);
    }
}
