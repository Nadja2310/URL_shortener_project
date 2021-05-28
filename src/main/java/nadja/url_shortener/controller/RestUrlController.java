package nadja.url_shortener.controller;

import nadja.url_shortener.dto.LongUrlDto;
import nadja.url_shortener.dto.ShortUrlDto;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.service.ShortenerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody ShortUrlDto convertLongUrl(@RequestBody LongUrlDto longUrlDto) {
        Url urlLong_getShort = shortenerService.createUrl(longUrlDto);
        return new ShortUrlDto(HOST + "/" + urlLong_getShort.getShortUrl());
    }
}
