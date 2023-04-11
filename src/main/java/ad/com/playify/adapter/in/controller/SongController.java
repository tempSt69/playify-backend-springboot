package ad.com.playify.adapter.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ad.com.playify.domain.entity.Song;
import ad.com.playify.domain.port.in.SongServicePort;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongServicePort songServicePort;

    @GetMapping
    public List<Song> getSongs() {
        return songServicePort.getAllSongs();
    }

    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songServicePort.createSong(song);
    }

    @GetMapping("/{id}")
    public Song getSongById(@RequestAttribute String id) {
        return songServicePort.getSongById(id);
    }

    @GetMapping("/stream/{id}")
    public void streamSong(@RequestAttribute String id) {
        songServicePort.streamSong(id);
    }
}
