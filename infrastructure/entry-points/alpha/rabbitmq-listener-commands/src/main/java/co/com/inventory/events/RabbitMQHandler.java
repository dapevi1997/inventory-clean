package co.com.inventory.events;

import co.com.inventory.events.data.Notification;
import co.com.inventory.mapper.JSONMapperImpl;
import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RabbitMQHandler {
    public static final String EVENTS_QUEUE = "events.queue";
    private final Logger logger = Logger.getLogger("RabbitMqEventHandler");

    private final MySqlRepository mySqlRepository;
    private final JSONMapperImpl jsonMapper;

    public RabbitMQHandler(MySqlRepository mySqlRepository, JSONMapperImpl jsonMapper) {
        this.mySqlRepository = mySqlRepository;
        this.jsonMapper = jsonMapper;
    }

    @RabbitListener(queues = EVENTS_QUEUE)
    public void listener(String message) throws ClassNotFoundException{
        Notification notification = Notification.from(message);
        if (notification.getType().equals("co.com.inventory.model.branch.events.BranchCreated")){
           BranchCreated branchCreated = (BranchCreated) jsonMapper.readFromJson(notification.getBody(), BranchCreated.class);

            mySqlRepository.saveBranch(branchCreated.getAggregateRootId(), branchCreated.getBranchName(), branchCreated.getBranchCountry(), branchCreated.getBranchCity())
                    .subscribe(branch -> {
                        logger.info(notification.toString());
                    });


        }


    }


}
