package my.expediteurDeTache.app.rabbitMQ;

import lombok.extern.slf4j.Slf4j;
import my.expediteurDeTache.app.objects.GlobalVariables;
import my.expediteurDeTache.app.dto.TaskDTO;
import my.expediteurDeTache.app.services.TaskService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQReceiver {

    @Autowired
    private TaskService taskService;

    @RabbitListener(queues = GlobalVariables.RESULT_QUEUE)
    public void receiveResult(TaskDTO result) {
        // Save the received result
        log.info("receive resultQ : "+result.toString());
        taskService.updateResult(result);
    }
}
