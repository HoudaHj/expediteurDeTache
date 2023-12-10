package my.expediteurDeTache.app;

import my.expediteurDeTache.app.dto.TaskDTO;
import my.expediteurDeTache.app.entities.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTests {
    @Test
    void testGettersAndSetters() {
        Task task = new Task(1L,"description","date",0);

        task.setTaskId(2L);
        assertEquals(2L, task.getTaskId());

        task.setDescription("description 2");
        assertEquals("description 2", task.getDescription());

        task.setModificationDate("10/12/2023 12:10:25");
        assertEquals("10/12/2023 12:10:25", task.getModificationDate());

        task.setCounter(1);
        assertEquals(1, task.getCounter());

    }

    @Test
    void testConstructorFromTaskDTOObject() {

        TaskDTO taskDTO = new TaskDTO(1L,"description","10/12/2023 10:09:55",10);

        Task task = new Task(taskDTO);

        assertEquals(taskDTO.getTaskId(), task.getTaskId());
        assertEquals(taskDTO.getDescription(), task.getDescription());
        assertEquals(taskDTO.getModificationDate(), task.getModificationDate());
        assertEquals(taskDTO.getCounter(), task.getCounter());

    }

    @Test
    void testToString() {
        Task task = new Task(1L, "Description", "12/10/2013 10:19:55", 5);
        String expectedString = "Task(taskId=1, description=Description, modificationDate=12/10/2013 10:19:55, counter=5)";

        assertEquals(expectedString, task.toString());
    }
}
