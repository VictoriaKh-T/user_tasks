package taskmaster.todo.service;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
