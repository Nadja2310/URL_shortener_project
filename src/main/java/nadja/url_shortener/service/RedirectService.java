package nadja.url_shortener.service;

import lombok.extern.slf4j.Slf4j;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.kafka.RedirectStat;
import nadja.url_shortener.repo.IUrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RedirectService {
    private final IUrlRepo urlRepo;
    @Autowired
    private  StatisticsProducerService statisticsProducerService;

    public RedirectService(IUrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    @Cacheable(value = "urlCache", key = "#searchShortUrl")
    public Url searchLongUrl(String searchShortUrl) {
        Optional<Url> urlFromRepo = urlRepo.getUrlByShortUrlAndExpiration_date(searchShortUrl);
        System.out.println("Cache not hit");
        Url url = checkingIfTheLinkIsFound(urlFromRepo);
        //kafkaStatisticsProducerService(url);
        return url;
    }

    private void kafkaStatisticsProducerService(Url url) {
        if (url != null) {
            //StatisticsProducerService statisticsProducerService = new StatisticsProducerService();
            RedirectStat redirectStat = new RedirectStat();
            redirectStat.setShortUrl(url.getShortUrl());
            redirectStat.setLongUrl(url.getLongUrl());
            redirectStat.setCurrentDate(LocalDate.now());
            statisticsProducerService.sendRedirectStat(redirectStat);
        }
    }

    public Url checkingIfTheLinkIsFound(Optional<Url> urlOptional) {
        return (urlOptional.isPresent() ? urlOptional.get() : null);
    }
}
