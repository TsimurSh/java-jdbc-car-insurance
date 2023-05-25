package demo.integration.repository;

import demo.model.User;
import demo.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UserRepositoryTest {

    private static final UserRepository userRepository = new UserRepository();

    private static final String USER_LOGIN = "Leo2";

    @Test
    void findUserTest() {
        User userTest = userRepository.findByLogin(USER_LOGIN);
        System.out.println(userTest);
        assertEquals("Leo2", userTest.getLogin());
        assertEquals("secret5", userTest.getPassword());
    }

    @Test
    void findAllUserTest() {
        List<User> all = userRepository.findAll();

        assertFalse(all.isEmpty());
    }
}
