package my.expediteurDeTache.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import my.expediteurDeTache.app.entities.Task;

public class TaskDTO {
    private Long taskId;

    private String description;

    private String modificationDate;

    private long counter;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public TaskDTO(@JsonProperty("taskId") final Long taskId,
                   @JsonProperty("description") final String description,
                   @JsonProperty("modificationDate") final String modificationDate,
                   @JsonProperty("counter") final long counter) {
        this.taskId = taskId;
        this.description = description;
        this.modificationDate = modificationDate;
        this.counter = counter;
    }

    public TaskDTO(Task task){
        this.taskId = task.getTaskId();
        this.description = task.getDescription();
        this.modificationDate = task.getModificationDate();
        this.counter = task.getCounter();
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "taskId=" + taskId +
                ", description='" + description + '\'' +
                ", modificationDate='" + modificationDate + '\'' +
                ", counter=" + counter +
                '}';
    }
}
