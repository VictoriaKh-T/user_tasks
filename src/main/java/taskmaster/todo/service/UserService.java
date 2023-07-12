package taskmaster.todo.service;

import java.util.Optional;
import taskmaster.todo.model.User;

public interface UserService {
    User save(User user);

    User update(User user);

    Optional<User> findById(Long id);

    Optional<User> findByLogin(String login);

}
