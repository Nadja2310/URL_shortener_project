package nadja.url_shortener.service;

import nadja.url_shortener.repo.UrlScheduledRepo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ExperiedRecordsCleaner {

    private UrlScheduledRepo urlScheduledRepo;

    public ExperiedRecordsCleaner(UrlScheduledRepo urlScheduledRepo) {
        this.urlScheduledRepo = urlScheduledRepo;
    }

    @Scheduled(fixedDelay = 100_000_000)
    public void deleteExpiredLinks() {
        urlScheduledRepo.deleteExpired();
    }
}
