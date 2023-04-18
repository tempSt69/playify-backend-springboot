package ad.com.playify.adapter.out.persistence.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import ad.com.playify.adapter.out.persistence.entity.UserJpa;

public interface UserRepository extends MongoRepository<UserJpa, String> {

    @Query("{ 'username' : ?0 }")
    Optional<UserJpa> findByUsername(String username);
}
