package nadja.url_shortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nadja.url_shortener.dto.UrlDto;
import nadja.url_shortener.repo.IUrlRepo;
import nadja.url_shortener.service.ShortenerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
class UrlControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ShortenerService shortenerService;

    @MockBean
    private IUrlRepo urlRepo;

    UrlDto longUrlDto= new UrlDto(1, "http://microsoft.com", LocalDate.of(2021,05,10));



    @Test
    void convertLongUrl() {

       Mockito.when(shortenerService.get_shortURL("\"http://microsoft.com")).thenReturn("http://localhost:8080/Yf74Nb4");
        mockMvc.perform(post("ddasda"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}