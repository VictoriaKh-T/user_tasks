package taskmaster.todo.dao;

import java.util.Optional;
import taskmaster.todo.model.User;

public interface UserDao {
    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByLogin(String login);

    User update(User user);
}
