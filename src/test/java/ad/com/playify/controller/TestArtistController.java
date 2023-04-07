package ad.com.playify.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import ad.com.playify.AppTests;
import ad.com.playify.model.Artist;
import ad.com.playify.service.ArtistService;

@SpringBootTest
@AutoConfigureMockMvc
public class TestArtistController extends AppTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistService artistService;

    @Test
    public void testGetArtist() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/artist"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("test " + content);
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(content, "[]");

        ArrayList<Artist> listFromResponse = (ArrayList<Artist>) objectMapper.readValue(content, Iterable.class);
        assertNotNull(listFromResponse);
        assertTrue(listFromResponse.isEmpty());
    }
}
