package ad.com.playify.adapter.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ad.com.playify.domain.entity.Artist;
import ad.com.playify.domain.port.in.ArtistServicePort;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistServicePort artistServicePort;

    @GetMapping
    public List<Artist> getArtists() {
        return artistServicePort.getAllArtists();
    }

    @PostMapping
    public Artist createArtist(@RequestBody Artist artist) {
        return artistServicePort.createArtist(artist);
    }
}
