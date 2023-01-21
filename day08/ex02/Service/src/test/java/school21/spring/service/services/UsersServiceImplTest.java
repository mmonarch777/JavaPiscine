package school21.spring.service.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.ApplicationConfigTest;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UsersServiceImplTest {
    private DataSource dataSource;
    private UsersRepository usersRepository;
    private UsersServiceImpl usersServiceImpl;
    private UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate;


    @BeforeEach
    public void init() throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfigTest.class);
        dataSource = context.getBean("HSQLDB", DataSource.class);
        usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        usersServiceImpl = new UsersServiceImpl(usersRepository);
    }

    @Test
    public void aLLMethodsTest() {
        assertEquals(usersRepository.findByEmail("2@mail.ru"), Optional.of(usersRepository.findById(1L)));
        List<User> listAll = new ArrayList<>();
        listAll.add(new User(0L, "1@mail.ru", "111"));
        listAll.add(new User(1L, "2@mail.ru", "222"));
        listAll.add(new User(2L, "3@mail.ru", "333"));
        listAll.add(new User(3L, "4@mail.ru", "444"));
        assertEquals(usersRepository.findAll(), listAll);

        usersRepository.delete(0L);
        Assertions.assertThrows(NullPointerException.class ,() -> usersRepository.findByEmail("romanbinel@gmail.com"));
        usersRepository.save(new User(null, "save@gmail.com", "3241d123dd4"));
        assertNotNull(usersRepository.findByEmail("save@gmail.com"));
        User user = usersRepository.findByEmail("save@gmail.com").get();
        System.out.println(user);;
        user.setEmail("update@gmail.com");
        usersRepository.update(user);
        assertNotNull(usersRepository.findByEmail("update@gmail.com"));
    }

    @Test
    public void passwordReturnTest() {
        usersServiceImpl.signUp("test1@gmail.com");
        User user = usersRepository.findByEmail("test1@gmail.com").get();
        System.out.println(user.getEmail() + " " + user.getPassword());
        assertNotNull(user.getPassword());
        assertNull(usersServiceImpl.signUp(null));
        assertNull(usersServiceImpl.signUp("4343"));
    }
}