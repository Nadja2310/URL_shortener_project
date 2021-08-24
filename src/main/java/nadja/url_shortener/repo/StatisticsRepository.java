package nadja.url_shortener.repo;

import nadja.url_shortener.entity.Statistics;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StatisticsRepository extends CrudRepository<Statistics, Integer> {
    List<Statistics> getStatisticsByCountNotNull();

    Statistics findStatisticsByShortUrl(String shortUrl);

    @Query("SELECT  s FROM Statistics s ORDER BY s.count DESC")
    List<Statistics> topURL();


    @Modifying
    @Query(value = "insert into statistics (id, short_url, long_url, count) VALUES(:id, :short_url, :long_url, :count) ON CONFLICT (id) DO UPDATE SET counter=statistics.count+1", nativeQuery = true)
    @Transactional
    void updateStatistics(@Param("short_url") String shortUrl, @Param("long_url") String longUrl, @Param("count") Long count);

}
