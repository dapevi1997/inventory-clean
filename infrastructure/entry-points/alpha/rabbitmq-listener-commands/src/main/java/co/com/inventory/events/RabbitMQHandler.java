package co.com.inventory.events;

import co.com.inventory.events.data.Notification;
import co.com.inventory.mapper.JSONMapperImpl;
import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.model.branch.events.ProductAdded;
import co.com.inventory.model.branch.events.UserRegistered;
import co.com.inventory.usecase.alpha.SaveBranchViewUseCase;
import co.com.inventory.usecase.alpha.SaveProductViewUseCase;
import co.com.inventory.usecase.alpha.SaveUserViewUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import java.util.logging.Logger;

@Component
public class RabbitMQHandler {
    public static final String EVENTS_QUEUE = "events.queue";
    private final Logger logger = Logger.getLogger("RabbitMqEventHandler");
    private final SaveBranchViewUseCase saveBranchViewUseCase;
    private final SaveProductViewUseCase saveProductViewUseCase;
    private final SaveUserViewUseCase saveUserViewUseCase;
    private final JSONMapperImpl jsonMapper;

    public RabbitMQHandler(
            SaveBranchViewUseCase saveBranchViewUseCase, SaveProductViewUseCase saveProductViewUseCase, SaveUserViewUseCase saveUserViewUseCase, JSONMapperImpl jsonMapper) {
        this.saveBranchViewUseCase = saveBranchViewUseCase;
        this.saveProductViewUseCase = saveProductViewUseCase;
        this.saveUserViewUseCase = saveUserViewUseCase;
        this.jsonMapper = jsonMapper;
    }

    @RabbitListener(queues = EVENTS_QUEUE)
    public void listener(String message) throws ClassNotFoundException{
        Notification notification = Notification.from(message);
        if (notification.getType().equals("co.com.inventory.model.branch.events.BranchCreated")){
           BranchCreated branchCreated = (BranchCreated) jsonMapper.readFromJson(notification.getBody(), BranchCreated.class);

           saveBranchViewUseCase.execute(branchCreated.getAggregateRootId(), branchCreated.getBranchName().toString(),
                           branchCreated.getBranchCountry(), branchCreated.getBranchCity())
                   .subscribe(branch -> {
                       logger.info(notification.toString());
                   });


        }
        if(notification.getType().equals("co.com.inventory.model.branch.events.ProductAdded")){
            ProductAdded productAdded = (ProductAdded) jsonMapper.readFromJson(notification.getBody(), ProductAdded.class);

            saveProductViewUseCase.execute(productAdded.getAggregateRootId(), productAdded.getProductId(),
                            productAdded.getProductName(), productAdded.getProductDescription(),
                            productAdded.getProductPrice(), productAdded.getProductInventoryStock(), productAdded.getProductCategory())
                    .subscribe(branch -> {
                        logger.info(notification.toString());
                    });
            logger.info(notification.toString());
        }
        if(notification.getType().equals("co.com.inventory.model.branch.events.UserRegistered")){
            UserRegistered userRegistered = (UserRegistered) jsonMapper.readFromJson(notification.getBody(), UserRegistered.class);

            saveUserViewUseCase.execute(userRegistered.getAggregateRootId(), userRegistered.getUserName(),
                            userRegistered.getUserLastName(), userRegistered.getUserPassword(),
                            userRegistered.getUserEmail(), userRegistered.getUserRole())
                    .subscribe(branch -> {
                        logger.info(notification.toString());
                    });
            logger.info(notification.toString());
        }


    }


}
