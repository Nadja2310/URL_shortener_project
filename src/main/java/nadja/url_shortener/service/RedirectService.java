package nadja.url_shortener.service;

import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import org.springframework.stereotype.Service;

@Service
public class RedirectService {
private IUrlRepo urlRepo;

    public Url searchLongUrl(String searchShortUrl) {
        Url url=urlRepo.findUrlByShortUrl(searchShortUrl);
         return url;
    }
}
