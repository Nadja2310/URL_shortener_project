package nadja.url_shortener.service;

import nadja.url_shortener.dto.UrlDto;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShortenerService {
    IUrlRepo urlRepo;

    //Нужно ли проверять что пользователь на этот длинный адрес уже создавал короткий?
    // ТОГДА нужна проверка при записи в базу данных
    //пока просто вносим данные в базу без проверки на уникальность
    public void save(Url newUrl) {
        urlRepo.save(newUrl);
    }

    public String get_shortURL(String long_Url) {
        StringShortenerHelper stringShortenerHelper = new StringShortenerHelper();
        return stringShortenerHelper.get_shortURL(long_Url);
    }

    public Url createUrl(UrlDto longUrlDto){
        Url url = new Url();
        url.setLongUrl(longUrlDto.getLongUrl());
        url.setUserID(0);
        url.setExpiration_date(LocalDate.now().plusDays(3));
        String shortenedUrl = new StringShortenerHelper().get_shortURL(longUrlDto.getLongUrl());
        url.setShortUrl(shortenedUrl);
        return url;
    }
}
