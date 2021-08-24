package nadja.url_shortener.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String longUrl;
    private String shortUrl;
    private Long count;

    public Statistics(String longUrl, String shortUrl, Long count) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.count = count;
    }

    public Statistics() {

    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public Long getCount() {
        return count;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
