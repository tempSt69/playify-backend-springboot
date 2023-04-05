package ad.com.playify.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ad.com.playify.controller.model.GenericId;
import ad.com.playify.model.Song;

@RestController
public class SongController {
    
    @GetMapping("/song")
    public Song[] getSong(){
        return null;
    }

    @GetMapping("/song/stream/{id}")
    public String getSongStream(@PathVariable String id){
        return null;
    }

    @PostMapping("/song")
    public ResponseEntity<GenericId> postSong(@RequestBody Song song){
        System.out.println(song);
        GenericId songId = new GenericId(UUID.randomUUID().toString());
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(songId);
    }

}
