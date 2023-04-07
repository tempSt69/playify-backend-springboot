package ad.com.playify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ad.com.playify.model.Song;
import ad.com.playify.repository.SongRepository;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    public Song getSong(String id) {
        return songRepository.findById(id).get();
    }
}