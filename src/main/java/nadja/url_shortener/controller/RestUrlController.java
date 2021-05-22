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
@RequestMapping(value = "/shortenerUrl", produces = "application/json")
        //consumes = "application/json", produces = "application/json")
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
    public @ResponseBody ShortUrlDto convertLongUrl(@RequestBody UrlDto longUrlDto) {
        Url urlLong_getShort = shortenerService.createUrl(longUrlDto);
        //longUrlDto.setId(urlLong_getShort.getId());
        //longUrlDto.setShortUrl(urlLong_getShort.getShortUrl());
        return new ShortUrlDto(HOST + "/" + urlLong_getShort.getShortUrl());
    }
}
