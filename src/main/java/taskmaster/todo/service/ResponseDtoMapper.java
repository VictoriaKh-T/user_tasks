package taskmaster.todo.service;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
