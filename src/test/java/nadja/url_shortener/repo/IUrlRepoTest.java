package nadja.url_shortener.repo;

import nadja.url_shortener.entity.Url;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IUrlRepoTest {

    @Autowired
    private IUrlRepo urlRepo;

    @Test
    void findUrlByShortUrl() {
        Url urlTest = new Url(1, "http://ok.com", "Y55555", 0, LocalDate.of(2021, 05, 24));

        Url urlSaved = urlRepo.save(urlTest);
        Url url = urlRepo.findUrlByShortUrl("Y55555");
        assertNotNull(url);
        assertEquals(urlTest.getLongUrl(), url.getLongUrl());
    }

    @Test
    void getUrlByShortUrlAndExpiration_date_then_expiredLongUrl() {
        Url urlTest = new Url(1, "http://microsoft85.com", "Yf74Nb8", 0, LocalDate.of(2021, 05, 24));
        Url url = urlRepo.save(urlTest);
        Optional<Url> expiredLongUrl = urlRepo.getUrlByShortUrlAndExpiration_date("Yf74Nb8");
        assertFalse(expiredLongUrl.isPresent());
    }
    @Test
    void getUrlByShortUrlAndExpiration_date_thenOk() {
        Url urlTest = new Url(1, "http://microsoft.com", "Yf74Nb4", 0, LocalDate.of(2021, 05, 31));

        Url url = urlRepo.save(urlTest);
        Optional<Url> expiredLongUrl = urlRepo.getUrlByShortUrlAndExpiration_date("Yf74Nb4");
        assertTrue(expiredLongUrl.isPresent());
        assertEquals(urlTest.getLongUrl(), expiredLongUrl.get().getLongUrl());
    }
}