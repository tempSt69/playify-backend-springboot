package ad.com.playify.adapter.out.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ad.com.playify.adapter.out.persistence.entity.ArtistJpa;
import ad.com.playify.domain.entity.Artist;

@Mapper
public interface ArtistMapper {
    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);

    Artist artistJpaToArtist(ArtistJpa artistJpa);

    ArtistJpa artistToArtistJpa(Artist artist);

    List<ArtistJpa> artistListToArtistJpaList(List<Artist> artist);

    List<Artist> artistJpaListToArtistList(List<ArtistJpa> artistJpa);

}
