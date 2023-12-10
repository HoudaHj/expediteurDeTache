package my.expediteurDeTache.app;

import my.expediteurDeTache.app.dto.TaskDTO;
import my.expediteurDeTache.app.entities.Task;
import my.expediteurDeTache.app.rabbitMQ.RabbitMQSender;
import my.expediteurDeTache.app.repositories.TaskRepository;
import my.expediteurDeTache.app.sericesImpl.TaskServiceImpl;
import my.expediteurDeTache.app.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private RabbitMQSender rabbitMQSender;

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    @Test
    void testCreateOrUpdateTask_NullTaskDTO() {
        assertThrows(NullPointerException.class, () -> taskServiceImpl.createOrUpdateTask(null));
    }

    @Test
    void testCreateOrUpdateTask_UpdateExistingTask() {
        Long taskId = 1L;
        String description = "first task";
        String modifiedDate = "10/12/2023 12:12:12";
        int counter = 1;
        TaskDTO taskDTO = new TaskDTO(taskId,description,modifiedDate,counter);
        Task task = new Task(taskDTO);
        Task existingTask = new Task(taskDTO);
        when(taskRepository.save(any())).thenReturn(task);
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(existingTask));

        assertNotNull(taskServiceImpl.createOrUpdateTask(taskDTO));
    }

    @Test
    void testCreateOrUpdateTask_CreateNewTask() {
        Long taskId = null;
        String description = "create task";
        String modifiedDate = "10/12/2023 12:12:12";
        int counter = 1;
        TaskDTO taskDTO = new TaskDTO(taskId,description,modifiedDate,counter);
        when(taskRepository.save(any())).thenReturn(new Task(taskDTO));

        assertNotNull(taskServiceImpl.createOrUpdateTask(taskDTO));
    }

    @Test
    void testCreateOrUpdateTask_NonExistentTaskID() {
        Long taskId = 100L;
        String description = "first task";
        String modifiedDate = "10/12/2023 12:12:12";
        int counter = 0;
        TaskDTO taskDTO = new TaskDTO(taskId,description,modifiedDate,counter);
        when(taskRepository.findById(taskDTO.getTaskId())).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> taskServiceImpl.createOrUpdateTask(taskDTO));

    }

    @Test
    void testUpdateResult() {
        Long taskId = 1L;
        String description = "first task";
        String modifiedDate = "10/12/2023 12:12:12";
        int counter = 0;
        TaskDTO taskDTO = new TaskDTO(taskId,description,modifiedDate,counter);
        taskServiceImpl.updateResult(taskDTO);
        verify(taskRepository, times(1)).save(Mockito.any());

    }
}
