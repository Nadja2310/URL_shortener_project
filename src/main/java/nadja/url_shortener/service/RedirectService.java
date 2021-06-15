package nadja.url_shortener.service;

import lombok.extern.slf4j.Slf4j;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RedirectService {
private final IUrlRepo urlRepo;

    public RedirectService(IUrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    @Cacheable(value = "urlCache", key = "#searchShortUrl")
    public Url searchLongUrl(String searchShortUrl) {
        log.info("searchShortUrl: {}", searchShortUrl);
        Optional<Url> urlRepo=this.urlRepo.getUrlByShortUrlAndExpiration_date(searchShortUrl);
        Url url=checkingIfTheLinkIsFound(urlRepo);
         return url;
    }
    public Url checkingIfTheLinkIsFound(Optional<Url> urlOptional){
        return (urlOptional.isPresent()?urlOptional.get():null);
    }
}
