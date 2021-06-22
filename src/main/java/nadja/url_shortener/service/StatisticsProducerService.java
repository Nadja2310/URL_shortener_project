package nadja.url_shortener.service;

import nadja.url_shortener.kafka.RedirectStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class StatisticsProducerService {
    @Autowired
    private KafkaTemplate<String, RedirectStat> kafkaTemplate;

    public void sendRedirectStat(RedirectStat stat) {
        ListenableFuture<SendResult<String, RedirectStat>> future = kafkaTemplate.send(
                "msg", stat.getShortUrl(), stat);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
