package nadja.url_shortener.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.util.Base64;

@Entity
//id (int, auto increment), short_url, long_url, expiration_date, user_id (always 0 for now)
public class ShortenedUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    String shortUrl;
    String longUrl;
    int userID=0;
    LocalDate expiration_date;

    public ShortenedUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public ShortenedUrl() {

    }

}
