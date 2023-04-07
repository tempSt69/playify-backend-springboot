package ad.com.playify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ad.com.playify.model.Artist;
import ad.com.playify.service.ArtistService;

@CrossOrigin
@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistService service;

    @GetMapping()
    public List<Artist> getArtist() {
        return service.getArtists();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Artist> postArtist(@RequestBody Artist artist) {
        Artist artistCreated = service.createArtist(artist);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                .body(artistCreated);
    }
}
