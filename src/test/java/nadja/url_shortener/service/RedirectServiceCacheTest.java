package nadja.url_shortener.service;

import lombok.extern.slf4j.Slf4j;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class RedirectServiceCacheTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IUrlRepo urlRepo;
    @Autowired
    WebApplicationContext wac;
    @MockBean
    private Url urlTest;


    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }
    @Test
    public void cacheTest() throws Exception {
        RedirectService redirectService=Mockito.spy(new RedirectService(urlRepo));
        Url urlTest = new Url(1, "http://microsoft85.com", "Yf74Nb8", LocalDate.now().plusDays(3));
        Url url = urlRepo.save(urlTest);
        System.out.println(redirectService.searchLongUrl(urlTest.getShortUrl()));
        System.out.println(redirectService.searchLongUrl(urlTest.getShortUrl()));

       Mockito.verify(redirectService, Mockito.times(2)).searchLongUrl(Mockito.anyString());
        Mockito.verify(redirectService, Mockito.times(1)).checkingIfTheLinkIsFound(Mockito.anyObject());
   }
}