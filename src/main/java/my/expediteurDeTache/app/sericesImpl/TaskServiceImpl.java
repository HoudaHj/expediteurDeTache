package my.expediteurDeTache.app.sericesImpl;

import my.expediteurDeTache.app.rabbitMQ.RabbitMQSender;
import my.expediteurDeTache.app.dto.TaskDTO;
import my.expediteurDeTache.app.entities.Task;
import my.expediteurDeTache.app.repositories.TaskRepository;
import my.expediteurDeTache.app.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public TaskDTO createOrUpdateTask(TaskDTO taskDTO) throws NullPointerException {
        if(taskDTO != null){
            if(taskDTO.getTaskId() != null){
                Optional<Task> optionalTask = taskRepository.findById(taskDTO.getTaskId());
                if(optionalTask.isPresent()){
                    taskDTO.setCounter(optionalTask.get().getCounter());
                }else{
                    throw new NullPointerException("Task "+taskDTO.getTaskId()+" NOT FOUND");
                }
            }
            Task task = new Task(taskDTO);
            Task savedTask = taskRepository.save(task);
            TaskDTO savedTaskDTO = new TaskDTO(savedTask);
            rabbitMQSender.sendTask(savedTaskDTO);
            return savedTaskDTO;
        }
        throw new NullPointerException("Task null");
    }
    @Override
    public void updateResult(TaskDTO taskDTO) {
        Task task = new Task(taskDTO);
        taskRepository.save(task);
    }


}
