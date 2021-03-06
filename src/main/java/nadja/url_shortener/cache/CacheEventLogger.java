package nadja.url_shortener.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

//@Slf4j
public abstract class CacheEventLogger implements CacheEventListener<Object,Object> {

    @Override
    public void onEvent(
            CacheEvent cacheEvent) {
        System.out.println("custom Caching event" +
                cacheEvent.getType() +
                cacheEvent.getKey() +
                cacheEvent.getOldValue() +
                cacheEvent.getNewValue()
        );
/*        log.info(
                "custom Caching event {} {} {} {}",
                cacheEvent.getType(),
                cacheEvent.getKey(),
                cacheEvent.getOldValue(),
                cacheEvent.getNewValue()
        );*/
    }

}
