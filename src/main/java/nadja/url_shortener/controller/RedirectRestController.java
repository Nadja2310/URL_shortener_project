package nadja.url_shortener.controller;

import nadja.url_shortener.dto.ShortUrlDto;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.service.RedirectService;
import org.springframework.http.HttpStatus;
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
    public ModelAndView convertLongUrl(@PathVariable ShortUrlDto shortUrl) {
        Url redirectUrl = redirectService.searchLongUrl(shortUrl.getShortUrl());
        if (redirectUrl == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "url is expired or does not exist");
        } else {
            RedirectView red = new RedirectView(redirectUrl.getLongUrl(), false);
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            return new ModelAndView(red);
        }
    }

}
