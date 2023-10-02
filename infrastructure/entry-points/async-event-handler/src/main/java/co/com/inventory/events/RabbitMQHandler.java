package co.com.inventory.events;

import co.com.inventory.events.data.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RabbitMQHandler {
    public static final String EVENTS_QUEUE = "events.queue";
    private final Logger logger = Logger.getLogger("RabbitMqEventHandler");

    @RabbitListener(queues = EVENTS_QUEUE)
    public void listener(String message) throws ClassNotFoundException{
        Notification notification = Notification.from(message);
        logger.info(notification.toString());

    }


}
