package taskmaster.todo.service.mapper;

import org.springframework.stereotype.Component;
import taskmaster.todo.model.Task;
import taskmaster.todo.model.dto.TaskRequestDto;
import taskmaster.todo.model.dto.TaskResponseDto;
import taskmaster.todo.service.RequestDtoMapper;
import taskmaster.todo.service.ResponseDtoMapper;

@Component
public class TaskMapper implements RequestDtoMapper<TaskRequestDto, Task>,
        ResponseDtoMapper<TaskResponseDto, Task> {
    @Override
    public Task mapToModel(TaskRequestDto dto) {
        Task task = new Task();
        task.setDescription(dto.getDescription());
        task.setToDo(dto.getToDo());
        return task;
    }

    @Override
    public TaskResponseDto mapToDto(Task task) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setToDo(task.getToDo());
        taskResponseDto.setDeleted(task.isDeleted());
        return taskResponseDto;
    }
}
