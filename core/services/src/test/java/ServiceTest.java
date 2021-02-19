import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.port.dao.art.ArtDao;
import org.port.dao.user.UserDao;
import org.port.data.enums.Category;
import org.port.data.model.Art;
import org.port.data.model.User;
import org.port.services.art.ArtService;
import org.port.services.art.ArtServiceImpl;
import org.port.services.user.UserService;
import org.port.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceTest {


    private ArtDao artDao = mock(ArtDao.class);

    private ArtServiceImpl artService = new ArtServiceImpl(artDao);

    private UserDao userDao = Mockito.mock(UserDao.class);

    private UserServiceImpl userService = new UserServiceImpl(userDao);


    @Test
    public void getData() {
        List<Art> artList = Stream.of(
                Art.builder().id("1").title("test")
                        .desc("test").category(Category.COMMISSIONED)
                        .url("google.com").date(new Date()).build(),
                Art.builder().id("2").title("test2")
                        .desc("test2").category(Category.FANART)
                        .url("google.com/2").date(new Date()).build()
        ).collect(Collectors.toList());
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

}
