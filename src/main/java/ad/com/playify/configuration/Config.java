package ad.com.playify.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ad.com.playify.adapter.out.persistence.adapter.ArtistJpaAdapter;
import ad.com.playify.adapter.out.persistence.adapter.SongJpaAdapter;
import ad.com.playify.domain.port.in.ArtistServicePort;
import ad.com.playify.domain.port.in.SongServicePort;
import ad.com.playify.domain.port.out.ArtistPersistencePort;
import ad.com.playify.domain.port.out.SongPersistencePort;
import ad.com.playify.domain.service.ArtistService;
import ad.com.playify.domain.service.SongService;

@Configuration
public class Config {

    @Bean
    public SongPersistencePort songPersistence() {
        return new SongJpaAdapter();
    }

    @Bean
    public SongServicePort songService() {
        return new SongService(songPersistence());
    }

    @Bean
    public ArtistPersistencePort artistPersistence() {
        return new ArtistJpaAdapter();
    }

    @Bean
    public ArtistServicePort artistService() {
        return new ArtistService(artistPersistence());
    }

}
