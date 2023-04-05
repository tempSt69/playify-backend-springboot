package ad.com.playify.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ad.com.playify.controller.model.GenericId;
import ad.com.playify.model.Artist;
import ad.com.playify.service.ArtistService;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired(required = false)
    private ArtistService service;

    @GetMapping()
    public List<Artist> getArtist() {
        return service.getArtists();
    }

    @PostMapping()
    public ResponseEntity<GenericId> postArtist(@RequestBody Artist artist) {
        System.out.println(artist);
        GenericId artistId = new GenericId(UUID.randomUUID().toString());
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(artistId);
    }
}
