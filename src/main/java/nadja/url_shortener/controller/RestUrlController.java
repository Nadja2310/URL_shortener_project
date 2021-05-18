package nadja.url_shortener.controller;

import nadja.url_shortener.dto.ShortUrlDto;
import nadja.url_shortener.dto.UrlDto;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.service.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

//, produces = "application/json"

@RestController
@RequestMapping(value = "/shortenerUrl")
public class RestUrlController {
    private final ShortenerService shortenerService;
    private final String HOST;

    public RestUrlController(ShortenerService shortenerService,
                             @Value("${host}") String host) {
        this.shortenerService = shortenerService;
        this.HOST = host;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ShortUrlDto convertLongUrl(@RequestBody UrlDto longUrlDto) {
        Url url = shortenerService.createUrl(longUrlDto);
        shortenerService.save(url);
        longUrlDto.setId(url.getId());
        return new ShortUrlDto(HOST + "/" + url.getShortUrl());
    }
}
