package nadja.url_shortener.service;

import nadja.url_shortener.entity.Url;
import nadja.url_shortener.service.ShortenerService;
import nadja.url_shortener.service.StringShortenerHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ShortenerServiceTest {
    StringShortenerHelper stringShortenerHelper=new StringShortenerHelper();
    @Test
    public void shortUrlToId_test(){

        String res = stringShortenerHelper.get_shortURL("https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html");
        assertEquals("bxcDY298", res);
    }

    @Test
    public void shortUrl_test_shorInputAddress(){
        String res = stringShortenerHelper.get_shortURL("do.de\345");
        assertEquals("2UMRZskU", res);

    }
}
