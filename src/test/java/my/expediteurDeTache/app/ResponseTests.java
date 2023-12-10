package my.expediteurDeTache.app;

import my.expediteurDeTache.app.dto.TaskDTO;
import my.expediteurDeTache.app.entities.Task;
import my.expediteurDeTache.app.objects.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTests {
    @Test
    void testGettersAndSetters() {
        Task task1 = new Task(1L,"description","10/12/2023 10:09:55",10);

        Response response = new Response("error 1 ",task1);

        response.setError("error 2");
        assertEquals("error 2", response.getError());

        Task task2 = new Task(2L,"description 2","15/12/2020 10:09:55",10);

        response.setResponse(task2);
        assertEquals(task2, response.getResponse());

    }

    @Test
    void testToString() {
        Task task = new Task(1L,"description","10/12/2023 10:09:55",10);
        Response response = new Response("error ",task);
        String expectedString = "Response(error=error , response=Task(taskId=1, description=description, modificationDate=10/12/2023 10:09:55, counter=10))";

        assertEquals(expectedString, response.toString());
    }
}
