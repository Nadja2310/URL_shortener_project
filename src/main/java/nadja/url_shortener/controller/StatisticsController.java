package nadja.url_shortener.controller;

import nadja.url_shortener.entity.Statistics;
import nadja.url_shortener.service.StatisticsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/statistics")
@Controller
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final String host;

    public StatisticsController(StatisticsService statisticsService, @Value("${host}") String host) {
        this.statisticsService = statisticsService;
        this.host = host;
    }

    @GetMapping()
    public String statistics(Model model) {
        List<Statistics> statistic = statisticsService.getTopUrl();
        model.addAttribute("statistic", statistic);
        model.addAttribute("host", host);
        return "statistics";
    }
}