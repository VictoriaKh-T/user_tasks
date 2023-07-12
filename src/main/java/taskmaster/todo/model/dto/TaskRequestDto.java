package taskmaster.todo.model.dto;

public class TaskRequestDto {

    private String description;
    private boolean toDo;
    private boolean isDeleted;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getToDo() {
        return toDo;
    }

    public void setToDo(boolean toDo) {
        this.toDo = toDo;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

}
