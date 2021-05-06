package nadja.url_shortener.repo;

import nadja.url_shortener.entity.ShortenedUrl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUrlRepo extends CrudRepository<ShortenedUrl,Integer> {

}
