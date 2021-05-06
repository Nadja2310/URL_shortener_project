package nadja.url_shortener.controller;

import nadja.url_shortener.service.ShortenerService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


public class Controller {
    ShortenerService shortenerService;

    public Controller(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

}
