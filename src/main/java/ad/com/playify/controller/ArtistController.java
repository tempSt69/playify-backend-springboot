package ad.com.playify.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ad.com.playify.controller.model.GenericId;
import ad.com.playify.model.Artist;

@RestController
public class ArtistController {

    @GetMapping("/artist")
    public List<Artist> getArtist() {
        List<Artist> artistList = new ArrayList<>();
        artistList.add(new Artist("1", "Artist 1"));
        artistList.add(new Artist("2", "Artist 2"));
        return artistList;
    }

    @PostMapping("/artist")
    public ResponseEntity<GenericId> postArtist(@RequestBody Artist artist) {
        System.out.println(artist);
        GenericId artistId = new GenericId(UUID.randomUUID().toString());
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(artistId);
    }
}
