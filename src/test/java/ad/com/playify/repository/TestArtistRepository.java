package ad.com.playify.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import ad.com.playify.AppTests;

@SpringBootTest
@AutoConfigureDataMongo
public class TestArtistRepository extends AppTests {

    @Autowired
    ArtistRepository artistRepository;

    // @BeforeTestClass
    // public void setUp() {
    // artistRepository.save(new Artist("foo", "bar", "baz"));
    // }

    @Test
    public void shouldBeNotEmpty() throws Exception {
        assertEquals(2, artistRepository.findAll().size());
    }
}
