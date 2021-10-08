import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.port.dao.art.ArtDao;
import org.port.dao.user.UserDao;
import org.port.data.enums.Category;
import org.port.data.model.Art;
import org.port.data.model.User;
import org.port.services.art.ArtServiceImpl;
import org.port.services.user.UserServiceImpl;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ServiceTest {

    @InjectMocks
    private ArtServiceImpl artService;
    @Mock
    private ArtDao artDao;
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserServiceImpl userService;

    WebClient webClient = WebClient.create("https://run.mocky.io/v3");

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getDatas() {
        List<Art> artLists = new ArrayList<Art>();
        Set<Art> allOrderLines = new HashSet<>();
        Art tmp = Art.builder().id("1").title("test")
                .desc("test").category(Category.COMMISSIONED)
                .url("google.com").date(new Date()).build();
        Art tmp2 = Art.builder().id("2").title("test2")
                .desc("test2").category(Category.FANART)
                .url("google.com/2").date(new Date()).build();
        allOrderLines.add(tmp);
        allOrderLines.add(tmp2);
        HashMap<Art, Integer> store = new HashMap<Art, Integer>();
        store.put(tmp, 1);
        store.put(tmp2, 2);
        Integer index = store.get(tmp);
        store.entrySet().stream()
                .sorted(Map.Entry.<Art, Integer>comparingByValue().reversed())
                .limit(3).collect(Collectors.toList()).stream().forEach( product -> {
        });
        store.get(tmp);
        allOrderLines.stream().findFirst().get();
    }

    @Test
    public void getData() {
        List<Art> artLists = new ArrayList<Art>();
        List<Art> artList = Stream.of(
            Art.builder().id("1").title("test")
                    .desc("test").category(Category.COMMISSIONED)
                    .url("google.com").date(new Date()).build(),
            Art.builder().id("2").title("test2")
                    .desc("test2").category(Category.FANART)
                    .url("google.com/2").date(new Date()).build()
            ).collect(Collectors.toList());

        artList.stream().forEach( item -> {
            artList.add(item);
        });
        when(artDao.findAll()).thenReturn(artList);
        assertEquals(2, artService.findAll().size());
    }

    @Test
    public void getIfExistsByUsername() {
        Optional<User> user = Optional.of(User.builder().id("asd").createdTime(new Date()).password("asdasd")
                .updateTime(new Date()).username("test").build());
        when(userDao.existsByUsername("asd")).thenReturn(true);
        assertTrue(userService.existsByUsername("asd"));
    }

    @Test
    public void findAll() {
        List<User> users = Stream.of(
                User.builder().id("asd").createdTime(new Date()).password("asdasd")
                        .updateTime(new Date()).username("test").build(),
                User.builder().id("asd2").createdTime(new Date()).password("asdasd2")
                        .updateTime(new Date()).username("test2").build()
        ).collect(Collectors.toList());
        when(userDao.findAll()).thenReturn(users);
        assertEquals(2, userService.findAll().size());
    }

    @Test
    public void testWebflux() {
        Mono<User> result = webClient.get()
                .uri("73b7fd07-dacc-41d0-b81a-b2205d623cda")
                .retrieve()
                .bodyToMono(User.class);
        result.subscribe();
    }

    @Test
    public void tesstsooo(){
        int[] asd =  {1, 0, 0, 0, 2, 2, 2};
        Arrays.stream(asd).count();
    }



}
