package taskmaster.todo.service;

import java.util.Optional;
import taskmaster.todo.model.Task;

public interface TaskService {
    Task save(Task task);

    Task update(Task task);

    void delete(Long taskId);

    Optional<Task> findById(Long id);

}
