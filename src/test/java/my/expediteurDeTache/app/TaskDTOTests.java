package my.expediteurDeTache.app;

import my.expediteurDeTache.app.dto.TaskDTO;
import my.expediteurDeTache.app.entities.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskDTOTests {

    @Test
    void testGettersAndSetters() {
        TaskDTO taskDTO = new TaskDTO(1L,"description","date",0);

        taskDTO.setTaskId(2L);
        assertEquals(2L, taskDTO.getTaskId());

        taskDTO.setDescription("description 2");
        assertEquals("description 2", taskDTO.getDescription());

        taskDTO.setModificationDate("10/12/2023 12:10:25");
        assertEquals("10/12/2023 12:10:25", taskDTO.getModificationDate());

        taskDTO.setCounter(1);
        assertEquals(1, taskDTO.getCounter());

    }

    @Test
    void testConstructorFromTaskObject() {

        Task task = new Task(1L,"description","10/12/2023 10:09:55",10);

        TaskDTO taskDTO = new TaskDTO(task);

        assertEquals(task.getTaskId(), taskDTO.getTaskId());
        assertEquals(task.getDescription(), taskDTO.getDescription());
        assertEquals(task.getModificationDate(), taskDTO.getModificationDate());
        assertEquals(task.getCounter(), taskDTO.getCounter());

    }

    @Test
    void testToString() {
        TaskDTO taskDTO = new TaskDTO(1L, "Description", "12/10/2013 10:19:55", 5);
        String expectedString = "TaskDTO{taskId=1, description='Description', modificationDate='12/10/2013 10:19:55', counter=5}";

        assertEquals(expectedString, taskDTO.toString());
    }
}
