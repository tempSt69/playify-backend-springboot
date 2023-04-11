package ad.com.playify.adapter.out.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ad.com.playify.adapter.out.persistence.entity.ArtistJpa;

@Repository
public interface ArtistRepository extends MongoRepository<ArtistJpa, String> {

}
