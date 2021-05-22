package nadja.url_shortener.service;

import nadja.url_shortener.dto.LongUrlDto;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class RedirectServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IUrlRepo urlRepo;
    @MockBean
    private Url urlTest;

    @Test
    void searchLongUrl() {
        Url urlRedirectServiceTest = new Url(1, "http://microsoft.com", "Yf74Nb4", 0, LocalDate.now().plusDays(3));
        when(urlRepo.findUrlByShortUrl("Yf74Nb4")).thenReturn(urlRedirectServiceTest);
        assertEquals(urlRedirectServiceTest.getLongUrl(),urlRepo.findUrlByShortUrl("Yf74Nb4").getLongUrl());
        assertEquals("Yf74Nb4",urlRepo.findUrlByShortUrl("Yf74Nb4").getShortUrl());
    }
}