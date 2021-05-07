package nadja.url_shortener.controller;

import nadja.url_shortener.dto.ShortUrlDto;
import nadja.url_shortener.dto.UrlDto;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import nadja.url_shortener.service.ShortenerService;
import nadja.url_shortener.service.StringShortenerHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/shortenerUrl")
public class RestUrlController {
    private final ShortenerService shortenerService;
    private final IUrlRepo urlRepo;
    private final String HOST;

    public RestUrlController(ShortenerService shortenerService, IUrlRepo urlRepo, @Value("${host}") String host) {
        this.shortenerService = shortenerService;
        this.urlRepo = urlRepo;
        this.HOST = host;
    }

    @PostMapping
    public ShortUrlDto convertLongUrl(@RequestBody UrlDto longUrlDto) {
        Url url = new Url();
        url.setLongUrl(longUrlDto.getLongUrl());
        url.setUserID(0);
        url.setExpiration_date(LocalDate.now().plusDays(3));
        String shortenedUrl = new StringShortenerHelper().get_shortURL(longUrlDto.getLongUrl());

        urlRepo.save(url);
        longUrlDto.setId(url.getId());
        return new ShortUrlDto(HOST + "/" + shortenedUrl);
    }
}
