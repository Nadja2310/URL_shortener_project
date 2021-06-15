package nadja.url_shortener.service;

import nadja.url_shortener.dto.LongUrlDto;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import org.springframework.stereotype.Service;

import javax.persistence.Cacheable;
import java.time.LocalDate;

@Service
public class ShortenerService {
    IUrlRepo urlRepo;

    public ShortenerService(IUrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public void save(Url newUrl) {
        urlRepo.save(newUrl);
    }

    public String get_shortURL(String long_Url) {
        return StringShortenerHelper.get_shortURL(long_Url);
    }

    public Url createUrl(LongUrlDto longUrlDto){
        Url addShortUrl = new Url();
        addShortUrl.setUserID(0);
        addShortUrl.setExpiration_date(LocalDate.now().plusDays(3));
        String shortenedUrl = new StringShortenerHelper().get_shortURL(longUrlDto.getLongUrl());
        addShortUrl.setShortUrl(shortenedUrl);
        save(addShortUrl);
        return addShortUrl;
    }
}
