package my.expediteurDeTache.app.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.expediteurDeTache.app.objects.GlobalVariables;
import my.expediteurDeTache.app.objects.Response;
import my.expediteurDeTache.app.dto.TaskDTO;
import my.expediteurDeTache.app.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Response> createOrUpdateTask(@RequestBody TaskDTO taskDTO) {

        try {
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern(GlobalVariables.DATE_TEMPLATE));
            taskDTO.setModificationDate(date);
            TaskDTO taskSaved = taskService.createOrUpdateTask(taskDTO);
            Response response = new Response("",taskSaved);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            Response response = new Response(e.getMessage(),null);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
