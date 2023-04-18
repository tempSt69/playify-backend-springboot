package ad.com.playify.adapter.out.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ad.com.playify.adapter.out.persistence.entity.SongJpa;
import ad.com.playify.adapter.out.persistence.mapper.SongMapper;
import ad.com.playify.adapter.out.persistence.repository.SongRepository;
import ad.com.playify.domain.entity.Song;
import ad.com.playify.domain.port.out.SongPersistencePort;

@Service
public class SongJpaAdapter implements SongPersistencePort {

    @Autowired
    private SongRepository songRepository;

    @Override
    public Song createSong(Song song) {
        SongJpa createdSong = SongMapper.INSTANCE.songToSongJpa(song);
        return SongMapper.INSTANCE.songJpaToSong(songRepository.save(createdSong));
    }

    @Override
    public List<Song> getAllSongs() {
        return SongMapper.INSTANCE.songJpaListToSongList(songRepository.findAll());
    }

    @Override
    public Song getSongById(String id) {
        Optional<SongJpa> songJpa = songRepository.findById(id);

        if (songJpa.isPresent()) {
            return SongMapper.INSTANCE.songJpaToSong(songJpa.get());
        }

        return null;
    }
}
