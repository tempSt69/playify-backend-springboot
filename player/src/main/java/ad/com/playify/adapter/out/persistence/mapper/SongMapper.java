package ad.com.playify.adapter.out.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ad.com.playify.adapter.out.persistence.entity.SongJpa;
import ad.com.playify.domain.entity.Song;

@Mapper
public interface SongMapper {

    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    SongJpa songToSongJpa(Song song);

    Song songJpaToSong(SongJpa songJpa);

    List<SongJpa> songListToSongJpaList(List<Song> song);

    List<Song> songJpaListToSongList(List<SongJpa> songJpa);

}
