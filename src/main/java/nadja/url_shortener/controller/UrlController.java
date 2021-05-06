package nadja.url_shortener.controller;

import nadja.url_shortener.dto.ShortUrlDto;
import nadja.url_shortener.dto.UrlDto;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import nadja.url_shortener.service.ShortenerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shortenerUrl")
public class UrlController {
    private final ShortenerService shortenerService;
    private final IUrlRepo urlRepo;
    private final String HOST;

    public UrlController(ShortenerService shortenerService, IUrlRepo urlRepo, @Value("${host}") String host) {
        this.shortenerService = shortenerService;
        this.urlRepo = urlRepo;
        this.HOST = host;
    }

    @PostMapping
    public ShortUrlDto convertLongUrl(@RequestBody UrlDto longUrlDto) {
        Url url = new Url();

        url.setLongUrl(longUrlDto.getLongUrl());
        urlRepo.save(url);
        String shortenedUrl = shortenerService.get_shortURL(url.getLongUrl());
        return new ShortUrlDto(HOST + "/" + shortenedUrl);
    }
}
