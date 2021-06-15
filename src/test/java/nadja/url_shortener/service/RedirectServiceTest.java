package nadja.url_shortener.service;

import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

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
    Url urlRedirectServiceTest = new Url(1, "http://microsoft.com", "Yf74Nb4", 0, LocalDate.now().plusDays(3));
    RedirectService redirectService=new RedirectService(urlRepo);

    @Test
    void searchLongUrl() {
        when(urlRepo.findUrlByShortUrl("Yf74Nb4")).thenReturn(urlRedirectServiceTest);
        assertEquals(urlRedirectServiceTest.getLongUrl(),urlRepo.findUrlByShortUrl("Yf74Nb4").getLongUrl());
        assertEquals("Yf74Nb4",urlRepo.findUrlByShortUrl("Yf74Nb4").getShortUrl());
    }
    @Test
    void expiredLongUrl() {
        Optional<Url> urlOptional=null;
        assertNull(redirectService.checkingIfTheLinkIsFound(urlOptional));
    }

    @Test
    void checkingIfTheLinkIsFound_thenIsUrl_notEmpty(){
        RedirectService redirectService=new RedirectService(urlRepo);
        Url urlRedirectServiceTest = new Url(1, "http://ok.com", "Y874Nb4", 0, LocalDate.of(2021,5,05));
        Optional<Url> urlOptional= Optional.of(urlRedirectServiceTest);
        Url res=redirectService.checkingIfTheLinkIsFound(urlOptional);
        assertNotNull(res);
        assertEquals(urlRedirectServiceTest.getLongUrl(),res.getLongUrl());
        assertEquals("Y874Nb4",res.getShortUrl());

    }
}