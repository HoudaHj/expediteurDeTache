package my.expediteurDeTache.app.rabbitMQ;

import lombok.extern.slf4j.Slf4j;
import my.expediteurDeTache.app.objects.GlobalVariables;
import my.expediteurDeTache.app.dto.TaskDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQSender {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTask(TaskDTO task) {
        rabbitTemplate.convertAndSend(GlobalVariables.TASK_QUEUE, task);
        log.info("taskQ sent : "+task.toString());
    }
}
