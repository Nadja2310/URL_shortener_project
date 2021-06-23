package nadja.url_shortener.kafka;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "currentDate")
public class RedirectStat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int id;
    public String longUrl;
    public String shortUrl;
    public LocalDate currentDate;

}
