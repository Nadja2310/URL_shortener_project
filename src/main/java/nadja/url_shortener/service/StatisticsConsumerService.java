package nadja.url_shortener.service;

import nadja.url_shortener.kafka.KafkaConsumerConfig;
import nadja.url_shortener.kafka.RedirectStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class StatisticsConsumerService {
    @Autowired
    private StatisticsService statisticsService;

    @KafkaListener(topics = "redirect_statistic", groupId = "statistics", containerFactory = "kafkaListenerContainerFactory")
    public void msgListener(List<RedirectStat> redirectStats) {

        Map<RedirectStat, Long> redirectStatisticMap = redirectStats.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (Map.Entry<RedirectStat, Long> entry : redirectStatisticMap.entrySet()) {

            statisticsService.updateRedirectStatistic(entry.getValue(), entry.getKey().getShortUrl(),
                    entry.getKey().getLongUrl());
        }
    }
}
