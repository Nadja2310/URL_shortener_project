package nadja.url_shortener.config;

import com.sun.istack.Nullable;
import nadja.url_shortener.controller.exeption.ShortUrlNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    HttpHeaders headers = new HttpHeaders();

    @ExceptionHandler(value =
            {ShortUrlNotFoundException.class})

    //@Nullable
    public ResponseEntity<Object> handleConflict(
            Exception ex, WebRequest request) {

        if (ex instanceof ShortUrlNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ShortUrlNotFoundException shortUrlNotFoundException = (ShortUrlNotFoundException) ex;

            return handleExceptionInternal(shortUrlNotFoundException, ex.getMessage(),
                    headers, status, request);
        } else {
            String bodyOfResponse = "This should be application specific";
            return handleExceptionInternal(ex, bodyOfResponse,
                    new HttpHeaders(), HttpStatus.CONFLICT, request);
        }

    }
}
