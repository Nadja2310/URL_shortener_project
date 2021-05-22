package nadja.url_shortener.service;

import nadja.url_shortener.dto.UrlDto;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import org.springframework.stereotype.Service;

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
      //  StringShortenerHelper stringShortenerHelper = new StringShortenerHelper();
        return StringShortenerHelper.get_shortURL(long_Url);
    }

    public Url createUrl(UrlDto longUrlDto){
        Url addShortUrl = new Url();
      //  addShortUrl.setLongUrl(longUrlDto.getLongUrl());
        addShortUrl.setUserID(0);
        addShortUrl.setExpiration_date(LocalDate.now().plusDays(3));
        String shortenedUrl = new StringShortenerHelper().get_shortURL(longUrlDto.getLongUrl());
        addShortUrl.setShortUrl(shortenedUrl);
        save(addShortUrl);
        return addShortUrl;
    }
}
