package nadja.url_shortener.service;

import lombok.Synchronized;
import nadja.url_shortener.entity.Statistics;
import nadja.url_shortener.repo.StatisticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public void save(Statistics statisticsRepo) {
        statisticsRepository.save(statisticsRepo);
    }

    //@Synchronized
    public void updateRedirectStatistic(Long count, String shortUrl, String longUrl) {
        Statistics entry = statisticsRepository.findStatisticsByShortUrl(shortUrl);
        if (entry!=null) {
            Statistics newEntry = new Statistics(longUrl,shortUrl,count);
            statisticsRepository.save(newEntry);
        } else {
            statisticsRepository.updateStatistics(shortUrl,longUrl,count);
        }
    }
    public List<Statistics> getTopUrl() {
        return statisticsRepository.topURL();
    }
}
