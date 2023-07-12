package taskmaster.todo.controller;

import java.util.List;
import java.util.Map;
import javax.security.sasl.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import taskmaster.todo.model.Task;
import taskmaster.todo.model.User;
import taskmaster.todo.model.dto.UserLoginDto;
import taskmaster.todo.model.dto.UserRegistrationDto;
import taskmaster.todo.security.jwt.JwtTokenProvider;
import taskmaster.todo.service.AuthenticationService;

@RestController
public class AuthenticationController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtTokenProvider jwtTokenProvider,
                                    AuthenticationService authenticationService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRegistrationDto userRegistrationDto) {
        authenticationService.register(userRegistrationDto.getLogin(),
                userRegistrationDto.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@RequestBody UserLoginDto userLoginDto)
            throws AuthenticationException {
        User user = authenticationService.login(userLoginDto.getLogin(),
                userLoginDto.getPassword());
        List<String> tasks = user.getTasks().stream()
                .map(Task::getDescription)
                .toList();
        String token = jwtTokenProvider.createToken(user.getLogin(), tasks);
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }
}
