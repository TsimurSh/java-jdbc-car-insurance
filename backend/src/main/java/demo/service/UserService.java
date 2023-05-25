package demo.service;

import demo.model.User;
import demo.model.UserPrincipal;
import demo.repository.UserRepository;

import java.util.Objects;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public Long checkUserExistsAndPasswordMatchedAndGetUserId(UserPrincipal loginData) {
        User user = userRepository.findByLogin(loginData.getLogin());
        boolean isUserExistsAndPasswordMatched = user != null && Objects.equals(user.getPassword(), loginData.getPassword());
        if (!isUserExistsAndPasswordMatched) {
            System.out.println("FORBIDDEN");
            return null;
        }
        return user.getId();
    }
}
