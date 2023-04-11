package ad.com.playify.adapter.out.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ad.com.playify.adapter.out.persistence.entity.SongJpa;

public interface SongRepository extends MongoRepository<SongJpa, String> {

}
