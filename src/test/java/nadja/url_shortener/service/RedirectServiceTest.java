package nadja.url_shortener.service;

import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class RedirectServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IUrlRepo urlRepo;
    @MockBean
    private Url urlTest;
    Url urlRedirectServiceTest = new Url(1, "http://microsoft.com", "Yf74Nb4", LocalDate.now().plusDays(3));
    RedirectService redirectService=new RedirectService(urlRepo);

    @Test
    void searchLongUrl() {
        when(urlRepo.findUrlByShortUrl("Yf74Nb4")).thenReturn(urlRedirectServiceTest);
        assertEquals(urlRedirectServiceTest.getLongUrl(),urlRepo.findUrlByShortUrl("Yf74Nb4").getLongUrl());
        assertEquals("Yf74Nb4",urlRepo.findUrlByShortUrl("Yf74Nb4").getShortUrl());
    }
    @Test
    void expiredLongUrl() {
        Url urlRedirectServiceTest = new Url(1, "http://ok.com", "Y555Nb4", LocalDate.of(2021,5,05));
        Optional<Url> urlOptional= urlRepo.getUrlByShortUrlAndExpiration_date(urlRedirectServiceTest.getShortUrl());
        assertNull(redirectService.checkingIfTheLinkIsFound(urlOptional));
    }

    @Test
    void checkingIfTheLinkIsFound_thenIsUrl_notEmpty(){
        Url urlRedirectServiceTest = new Url(1, "http://ok.com", "Y874Nb4",LocalDate.of(2021,5,05));
        Optional<Url> urlOptional= Optional.of(urlRedirectServiceTest);
        Url res=redirectService.checkingIfTheLinkIsFound(urlOptional);
        assertNotNull(res);
        assertEquals(urlRedirectServiceTest.getLongUrl(),res.getLongUrl());
        assertEquals("Y874Nb4",res.getShortUrl());

    }
    @Test
    void searchLongUrl_url_isNull(){
        Url urlTest = new Url(1, "http://microsoft85.com", "Yf74Nb8", LocalDate.of(2021, 05, 24));
        Url url = urlRepo.save(urlTest);
        //todo rewrite moc Optional
        System.out.println("find "+redirectService.searchLongUrl(urlTest.getShortUrl()));
        assertNull(redirectService.searchLongUrl(urlTest.getShortUrl()));
    }
}