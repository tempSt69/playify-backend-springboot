package ad.com.playify.adapter.out.persistence.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ad.com.playify.adapter.out.persistence.entity.ArtistJpa;
import ad.com.playify.adapter.out.persistence.mapper.ArtistMapper;
import ad.com.playify.adapter.out.persistence.repository.ArtistRepository;
import ad.com.playify.domain.entity.Artist;
import ad.com.playify.domain.port.out.ArtistPersistencePort;

@Service
public class ArtistJpaAdapter implements ArtistPersistencePort {

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public Artist createArtist(Artist artist) {
        ArtistJpa createdArtist = ArtistMapper.INSTANCE.artistToArtistJpa(artist);
        return ArtistMapper.INSTANCE.artistJpaToArtist(artistRepository.save(createdArtist));
    }

    @Override
    public List<Artist> getAllArtists() {
        return ArtistMapper.INSTANCE.artistJpaListToArtistList(artistRepository.findAll());
    }
}
