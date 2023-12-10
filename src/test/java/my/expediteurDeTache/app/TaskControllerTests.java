package my.expediteurDeTache.app;

import my.expediteurDeTache.app.controllers.TaskController;
import my.expediteurDeTache.app.dto.TaskDTO;
import my.expediteurDeTache.app.objects.Response;
import my.expediteurDeTache.app.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TaskControllerTests {
    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void testCreateOrUpdateTask_Success() {
        Long taskId = null;//id is null to create a new task
        String description = "create task";
        String modifiedDate = "10/12/2023 12:12:12";
        int counter = 0;

        TaskDTO taskDTO = new TaskDTO(taskId,description,modifiedDate,counter);
        when(taskService.createOrUpdateTask(taskDTO)).thenReturn(taskDTO);

        ResponseEntity<Response> responseEntity = taskController.createOrUpdateTask(taskDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testCreateOrUpdateTask_Failure() {

        Long taskId = 100L; //id does not exist
        String description = "task does not exist";
        String modifiedDate = "10/12/2023 12:12:12";
        int counter = 5;

        TaskDTO taskDTO = new TaskDTO(taskId,description,modifiedDate,counter);
        when(taskService.createOrUpdateTask(taskDTO)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<Response> responseEntity = taskController.createOrUpdateTask(taskDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
