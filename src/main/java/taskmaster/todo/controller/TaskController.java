package taskmaster.todo.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskmaster.todo.exeption.TaskNotFoundException;
import taskmaster.todo.exeption.UserNotFoundException;
import taskmaster.todo.model.Task;
import taskmaster.todo.model.User;
import taskmaster.todo.model.dto.TaskRequestDto;
import taskmaster.todo.model.dto.TaskResponseDto;
import taskmaster.todo.service.RequestDtoMapper;
import taskmaster.todo.service.ResponseDtoMapper;
import taskmaster.todo.service.TaskService;
import taskmaster.todo.service.UserService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    private final RequestDtoMapper<TaskRequestDto, Task> taskRequestDtoMapper;
    private final ResponseDtoMapper<TaskResponseDto, Task> taskResponseDtoMapper;

    public TaskController(TaskService taskService, UserService userService,
                          RequestDtoMapper<TaskRequestDto, Task> taskRequestDtoMapper,
                          ResponseDtoMapper<TaskResponseDto, Task> taskResponseDtoMapper) {
        this.taskService = taskService;
        this.userService = userService;
        this.taskRequestDtoMapper = taskRequestDtoMapper;
        this.taskResponseDtoMapper = taskResponseDtoMapper;
    }

    @PostMapping("/complete")
    public TaskResponseDto add(@RequestBody TaskRequestDto taskRequestDto, Authentication auth) {
        Task task = taskRequestDtoMapper.mapToModel(taskRequestDto);
        User user = getUserByLogin(auth.getName());
        taskService.save(task);
        user.getTasks().add(task);
        userService.update(user);
        return taskResponseDtoMapper.mapToDto(task);
    }

    @GetMapping("/by-user")
    public List<TaskResponseDto> getTaskByUser(Authentication auth) {
        User user = getUserByLogin(auth.getName());
        return user.getTasks().stream()
                .map(taskResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public TaskResponseDto update(@PathVariable Long id,
                                  @RequestBody TaskRequestDto taskRequestDto) {
        Task task = taskRequestDtoMapper.mapToModel(taskRequestDto);
        task.setId(id);
        taskService.update(task);
        return taskResponseDtoMapper.mapToDto(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Authentication auth) {
        Task task = taskService.findById(id).orElseThrow(
                () -> new TaskNotFoundException("task with id " + id + "not found"));
        task.setDeleted(true);
        User user = getUserByLogin(auth.getName());
        user.removeTask(task);
        userService.update(user);
        taskService.update(task);
    }

    private User getUserByLogin(String login) {
        return userService.findByLogin(login)
                .orElseThrow(()
                        -> new UserNotFoundException("User with login "
                        + login + " not found"));
    }

}
