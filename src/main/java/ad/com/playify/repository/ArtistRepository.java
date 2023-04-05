package ad.com.playify.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import ad.com.playify.model.Artist;

@Service
public interface ArtistRepository extends MongoRepository<Artist, String>{
    
}
