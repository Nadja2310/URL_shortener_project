package nadja.url_shortener.repo;

import nadja.url_shortener.entity.Url;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UrlScheduledRepo extends CrudRepository<Url, Integer> {

    @Transactional
    @Modifying
    @Query("delete from Url where current_timestamp > expiration_date")
    void deleteExpired();
}
