package nadja.url_shortener.service;

import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedirectService {
private final IUrlRepo urlRepo;

    public RedirectService(IUrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public Url searchLongUrl(String searchShortUrl) {
        Optional<Url> urlRepo=this.urlRepo.getUrlByShortUrlAndExpiration_date(searchShortUrl);
        Url url=checkingIfTheLinkIsFound(urlRepo);
         return url;
    }
    public Url checkingIfTheLinkIsFound(Optional<Url> urlOptional){
        return (urlOptional.isPresent()?urlOptional.get():null);
    }
}
