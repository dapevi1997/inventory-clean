package co.com.inventory.events;

import co.com.inventory.events.data.Notification;
import co.com.inventory.mapper.JSONMapper;
import co.com.inventory.model.branch.events.ProductAdded;
import co.com.inventory.controller.SocketController;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import java.util.logging.Logger;

@Component
public class RabbitMQHandlerGama {
    public static final String PROXY_QUEUE = "proxy.queue";
    private final Logger logger = Logger.getLogger("RabbitMqEventHandler");
    private final JSONMapper jsonMapper;
    private final SocketController socketController;

    public RabbitMQHandlerGama(JSONMapper jsonMapper, SocketController socketController) {
        this.jsonMapper = jsonMapper;
        this.socketController = socketController;
    }

    @RabbitListener(queues = PROXY_QUEUE)
    public void listener(String message) throws ClassNotFoundException{
        Notification notification = Notification.from(message);
        if(notification.getType().equals("co.com.inventory.model.branch.events.ProductAdded")){
            ProductAdded productAdded = (ProductAdded) jsonMapper.readFromJson(notification.getBody(), ProductAdded.class);
            socketController.sendProductAdded("", productAdded);


            logger.info(notification.toString());
        }



    }


}
