package ad.com.playify.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import ad.com.playify.model.Song;

@Service
public interface SongRepository extends MongoRepository<Song, String> {

}
