package taskmaster.todo.service;

import javax.security.sasl.AuthenticationException;
import taskmaster.todo.model.User;

public interface AuthenticationService {
    User register(String login, String password);

    User login(String login, String password) throws AuthenticationException;

}
