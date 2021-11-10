package nadja.url_shortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nadja.url_shortener.controller.exeption.ShortUrlNotFoundException;
import nadja.url_shortener.dto.LongUrlDto;
import nadja.url_shortener.dto.ShortUrlDto;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import nadja.url_shortener.service.RedirectService;
import nadja.url_shortener.service.ShortenerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RedirectRestControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext wac;
    @MockBean
    private RedirectService redirectService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Test
    void convertLongUrl() throws Exception {
        Url urlTest = new Url(1, "http://microsoft.com", "Yf74Nb4", LocalDate.now().plusDays(3));
        String shortUrlDtoTest="Yf74Nb4";
        when(redirectService.searchLongUrl("Yf74Nb4")).thenReturn(urlTest);

        mockMvc.perform(get("/"+shortUrlDtoTest))
                .andExpect(status().isMovedPermanently())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.header().string("Location", urlTest.getLongUrl()))
                .andReturn();
    }
    @Test
    void convertLongUrl_Exception () throws Exception {
        Url urlTest = new Url(1, "http://microsoftExciption.com", "Yf74Nb8", LocalDate.now().minusDays(1));
        String shortUrlDtoTest="Yf74Nb8";
        when(redirectService.searchLongUrl("Yf74Nb8")).thenReturn(null);

        mockMvc.perform(get("/"+shortUrlDtoTest))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}