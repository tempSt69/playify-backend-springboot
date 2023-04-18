package ad.com.playify.domain.port.out;

import ad.com.playify.domain.entity.User;

public interface UserPersistencePort {

    User getUserByUsername(String username);

    User createUser(User user);
}
