package nadja.url_shortener.repo;

import nadja.url_shortener.entity.Url;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUrlRepo extends CrudRepository<Url, Integer> {
    Url findUrlByShortUrl(String shortUrl);

    @Query("select s from Url s where s.shortUrl = :shortUrl and s.expiration_date >= current_date")
    Optional<Url> getUrlByShortUrlAndExpiration_date(@Param("shortUrl") String shortUrl);
}
