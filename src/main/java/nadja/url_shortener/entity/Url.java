package nadja.url_shortener.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "url")
public class Url implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String shortUrl;
    private String longUrl;
    private LocalDate expiration_date;

    public Url() {
    }

    public Url(int id,String longUrl,String shortUrl,LocalDate expiration_date) {
        this.id = id;
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.expiration_date = expiration_date;
    }

    public LocalDate getExpiration_date() {
        return expiration_date;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public int getId() {
        return id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public void setExpiration_date(LocalDate expiration_date) {
        this.expiration_date = expiration_date;
    }
}
