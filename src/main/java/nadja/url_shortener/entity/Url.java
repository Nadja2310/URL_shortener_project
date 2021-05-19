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
    private String shortUrl;
    private String longUrl;
    private int userID=0;
    private LocalDate expiration_date;

    public Url() {
    }

    public Url(int id,String longUrl,String shortUrl, int userID, LocalDate expiration_date) {
        this.id = id;
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.userID = userID;
        this.expiration_date = expiration_date;
    }

    public int getUserID() {
        return userID;
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

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setExpiration_date(LocalDate expiration_date) {
        this.expiration_date = expiration_date;
    }
}
