package nadja.url_shortener.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
//id (int, auto increment), short_url, long_url, expiration_date, user_id (always 0 for now)
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    String shortUrl;
    String longUrl;
    int userID=0;
    LocalDate expiration_date;

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

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setExpiration_date(LocalDate expiration_date) {
        this.expiration_date = expiration_date;
    }
}
