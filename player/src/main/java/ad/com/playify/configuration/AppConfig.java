package ad.com.playify.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import ad.com.playify.adapter.out.auth.service.AuthService;
import ad.com.playify.adapter.out.persistence.adapter.ArtistJpaAdapter;
import ad.com.playify.adapter.out.persistence.adapter.SongJpaAdapter;
import ad.com.playify.adapter.out.persistence.adapter.UserJpaAdapter;
import ad.com.playify.adapter.out.storage.service.StorageService;
import ad.com.playify.domain.port.in.ArtistServicePort;
import ad.com.playify.domain.port.in.SongServicePort;
import ad.com.playify.domain.port.out.ArtistPersistencePort;
import ad.com.playify.domain.port.out.AuthPort;
import ad.com.playify.domain.port.out.SongPersistencePort;
import ad.com.playify.domain.port.out.UserPersistencePort;
import ad.com.playify.domain.service.ArtistService;
import ad.com.playify.domain.service.SongService;
import ad.com.playify.domain.service.UserService;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public AuthPort auth() {
        return new AuthService();
    }

    @Bean
    public UserPersistencePort userPersistence() {
        return new UserJpaAdapter();
    }

    @Bean
    public UserService userService() {
        return new UserService(userPersistence(), auth());
    }

    @Bean
    public SongPersistencePort songPersistence() {
        return new SongJpaAdapter();
    }

    @Bean
    public StorageService storageService() {
        return new StorageService();
    }

    @Bean
    public SongServicePort songService() {
        return new SongService(songPersistence(), storageService());
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
