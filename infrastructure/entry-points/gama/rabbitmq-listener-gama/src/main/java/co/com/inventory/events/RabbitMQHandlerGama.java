package co.com.inventory.events;

import co.com.inventory.events.data.Notification;
import co.com.inventory.events.utils.Mapper;
import co.com.inventory.mapper.JSONMapper;
import co.com.inventory.mapper.JSONMapperImpl;
import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.model.branch.events.ProductAdded;
import co.com.inventory.model.branch.events.ProductSoldWholesale;
import co.com.inventory.model.branch.events.UserRegistered;
import co.com.inventory.usecase.alpha.SaveBranchViewUseCase;
import co.com.inventory.usecase.alpha.SaveProductViewUseCase;
import co.com.inventory.usecase.alpha.SaveUserViewUseCase;
import co.com.inventory.usecase.alpha.SaveWholesaleViewUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.logging.Logger;

@Component
public class RabbitMQHandlerGama {
    public static final String PROXY_QUEUE = "proxy.queue";
    private final Logger logger = Logger.getLogger("RabbitMqEventHandler");
    private final JSONMapper jsonMapper;

    public RabbitMQHandlerGama(JSONMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @RabbitListener(queues = PROXY_QUEUE)
    public void listener(String message) throws ClassNotFoundException{
        Notification notification = Notification.from(message);
        if(notification.getType().equals("co.com.inventory.model.branch.events.ProductAdded")){
            ProductAdded productAdded = (ProductAdded) jsonMapper.readFromJson(notification.getBody(), ProductAdded.class);

            logger.info(notification.toString());
        }



    }


}
