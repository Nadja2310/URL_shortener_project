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
class RedirectService_SearchLongUrl_Test {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IUrlRepo urlRepo;
    @MockBean
    private Url urlTest;


    @Test
    void searchLongUrl_thenIsUrl_notEmpty(){
        RedirectService redirectService=new RedirectService(urlRepo);
        Url urlRedirectServiceTest = new Url(1, "http://ok.com", "Y874Nb4", LocalDate.now().plusDays(3));
        Url url = urlRepo.save(urlRedirectServiceTest);
        System.out.println(url+"  urlRepo");
        Url expectedUrl=redirectService.searchLongUrl(urlRedirectServiceTest.getShortUrl());
        System.out.println("redirect service "+expectedUrl);
        assertEquals(expectedUrl.getLongUrl(),urlRedirectServiceTest.getLongUrl());
        assertEquals("Y874Nb4",urlRedirectServiceTest.getShortUrl());

    }
    @Test
    void searchLongUrl_url_isNull(){
        RedirectService redirectService=new RedirectService(urlRepo);
        Url urlTest = new Url(1, "http://microsoft85.com", "Yf74Nb8",LocalDate.of(2021, 05, 24));
        Url url = urlRepo.save(urlTest);
        assertNull(redirectService.searchLongUrl(urlTest.getShortUrl()));
    }
}