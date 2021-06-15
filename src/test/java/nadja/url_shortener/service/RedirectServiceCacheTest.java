package nadja.url_shortener.service;

import lombok.extern.slf4j.Slf4j;
import nadja.url_shortener.entity.Url;
import nadja.url_shortener.repo.IUrlRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class RedirectServiceCacheTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IUrlRepo urlRepo;
    @Autowired
    WebApplicationContext wac;
    @MockBean
    private Url urlTest;
    Url urlRedirectServiceTest1 = new Url(1, "http://microsoft.com", "Yf74Nb4", 0, LocalDate.now().plusDays(3));
    Url urlRedirectServiceTest2 = new Url(1, "http://yaoo.com", "1232333", 0, LocalDate.now().plusDays(3));
    Url urlRedirectServiceTest3 = new Url(1, "http://my.com", "Y555555", 0, LocalDate.now().plusDays(3));
    RedirectService redirectService=new RedirectService(urlRepo);

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }
    @Test
    public void cacheTest() throws Exception {
        String shortUrlDtoTest="Y555555";
        when(redirectService.searchLongUrl("Y555555")).thenReturn(urlRedirectServiceTest3);

        mockMvc.perform(get("/"+shortUrlDtoTest))
                .andExpect(status().isMovedPermanently())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.header().string("Location", urlTest.getLongUrl()))
                .andReturn();
        log.info("created user: {}", redirectService.searchLongUrl("Y555555"));
    }
}