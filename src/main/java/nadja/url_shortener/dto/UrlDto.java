package nadja.url_shortener.dto;

import java.time.LocalDate;

public class UrlDto {
    private int id;
    String shortUrl;
    String longUrl;
    int userID=0;
    LocalDate expiration_date;

    public UrlDto(int id, String longUrl, LocalDate expiration_date) {
        this.id = id;
        this.longUrl = longUrl;
        this.expiration_date = expiration_date;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public void setId(int id) {
        this.id = id;
    }
}
