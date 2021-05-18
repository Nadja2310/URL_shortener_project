package nadja.url_shortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nadja.url_shortener.dto.UrlDto;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import nadja.url_shortener.service.ShortenerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class RestUrlControllerTest {
    @Autowired
private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext wac;
    @MockBean
    private ShortenerService shortenerService;

    @MockBean
    private IUrlRepo urlRepo;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }
    @Test
    void convertLongUrl() throws Exception {
        Url urlTest=new Url(1,"http://microsoft.com","Yf74Nb4",0, LocalDate.now().plusDays(3));
        when(shortenerService.createUrl(any(UrlDto.class))).thenReturn(urlTest);
        //when(shortenerService.save(urlTest));

        mockMvc.perform(post("/shortenerUrl")
                        .accept(MediaType.TEXT_PLAIN )
        .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"long_url\": \"http://microsoft.com\"}")
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("{\"$.shortUrl\":\"http://localhost:8080/Yf74Nb4\"}"));

    }
}