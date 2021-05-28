package nadja.url_shortener.dto;

import java.time.LocalDate;

public class LongUrlDto {
    String longUrl;

    public LongUrlDto() {}

    public LongUrlDto(String longUrl){
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

}
