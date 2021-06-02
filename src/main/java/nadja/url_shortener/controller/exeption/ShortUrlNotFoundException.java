package nadja.url_shortener.controller.exeption;

public class ShortUrlNotFoundException extends Exception{
    private String shortUrl;


    public static ShortUrlNotFoundException createWith(String shortUrl) {
        return new ShortUrlNotFoundException(shortUrl);
    }
    private ShortUrlNotFoundException(String shortUrl){
        this.shortUrl=shortUrl;
    }

    @Override
    public String getMessage() {
        return "Url " + shortUrl + " not found";
    }
}
