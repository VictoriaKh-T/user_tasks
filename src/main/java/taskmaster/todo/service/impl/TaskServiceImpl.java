package taskmaster.todo.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Component;
import taskmaster.todo.dao.TaskDao;
import taskmaster.todo.model.Task;
import taskmaster.todo.service.TaskService;

@Component
public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;

    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Task save(Task task) {
        return taskDao.save(task);
    }

    @Override
    public Task update(Task task) {
        return taskDao.update(task);
    }

    @Override
    public void delete(Long taskId) {
        taskDao.delete(taskId);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskDao.findById(id);
    }
}
