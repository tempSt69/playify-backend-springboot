package ad.com.playify.adapter.out.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ad.com.playify.adapter.out.persistence.entity.UserJpa;
import ad.com.playify.domain.entity.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserJpa userToUserJpa(User user);

    User userJpaToUser(UserJpa userJpa);

    List<UserJpa> userListToUserJpaList(List<User> user);

    List<User> userJpaListToUserList(List<UserJpa> userJpa);

}
