package ad.com.playify.domain.port.in;

import ad.com.playify.domain.entity.User;

public interface UserServicePort {
    User register(User user);

    String login(User user);
}
