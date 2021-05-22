package nadja.url_shortener.dto;

public class ShortUrlDto {
    String shortUrl;

    public ShortUrlDto(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
