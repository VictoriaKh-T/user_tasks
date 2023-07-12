package taskmaster.todo.service.impl;

import java.util.Optional;
import javax.security.sasl.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import taskmaster.todo.model.User;
import taskmaster.todo.service.AuthenticationService;
import taskmaster.todo.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String login, String password) {
        User user = new User();
        user.setPassword(password);
        user.setLogin(login);
        userService.save(user);
        return user;
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> user = userService.findByLogin(login);
        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new AuthenticationException("Incorrect username or password!");
        }
        return user.get();
    }
}
