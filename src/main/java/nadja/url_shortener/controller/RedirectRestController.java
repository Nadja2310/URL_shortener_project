package nadja.url_shortener.controller;

import nadja.url_shortener.controller.exeption.ShortUrlNotFoundException;
import nadja.url_shortener.dto.ShortUrlDto;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RedirectRestController {
    private final RedirectService redirectService;

    public RedirectRestController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping("/{shortUrl}")
    public ModelAndView convertLongUrl(@PathVariable ShortUrlDto shortUrl) throws ShortUrlNotFoundException {
        Url redirectUrl = redirectService.searchLongUrl(shortUrl.getShortUrl());
        if (redirectUrl == null) {
            throw ShortUrlNotFoundException.createWith(shortUrl.getShortUrl());
        } else {
            RedirectView red = new RedirectView(redirectUrl.getLongUrl(), false);
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            return new ModelAndView(red);
        }
    }
}
