package my.expediteurDeTache.app.services;

import my.expediteurDeTache.app.dto.TaskDTO;
import my.expediteurDeTache.app.entities.Task;


public interface TaskService {
    public TaskDTO createOrUpdateTask(TaskDTO task) throws NullPointerException;
    public void updateResult(TaskDTO task);
}
