package co.com.inventory.events;

import co.com.inventory.events.data.Notification;
import co.com.inventory.events.utils.Mapper;
import co.com.inventory.mapper.JSONMapperImpl;
import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.events.*;
import co.com.inventory.usecase.alpha.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.logging.Logger;

@Component
public class RabbitMQHandler {
    public static final String EVENTS_QUEUE = "events.queue";
    private final Logger logger = Logger.getLogger("RabbitMqEventHandler");
    private final SaveBranchViewUseCase saveBranchViewUseCase;
    private final SaveProductViewUseCase saveProductViewUseCase;
    private final SaveUserViewUseCase saveUserViewUseCase;
    private final SaveWholesaleViewUseCase saveWholesaleViewUseCase;
    private final SaveRetailViewUseCase saveRetailViewUseCase;
    private final UpdateProductViewUseCase updateProductViewUseCase;
    private final JSONMapperImpl jsonMapper;

    public RabbitMQHandler(
            SaveBranchViewUseCase saveBranchViewUseCase, SaveProductViewUseCase saveProductViewUseCase, SaveUserViewUseCase saveUserViewUseCase, SaveWholesaleViewUseCase saveWholesaleViewUseCase, SaveRetailViewUseCase saveRetailViewUseCase, UpdateProductViewUseCase updateProductViewUseCase, JSONMapperImpl jsonMapper) {
        this.saveBranchViewUseCase = saveBranchViewUseCase;
        this.saveProductViewUseCase = saveProductViewUseCase;
        this.saveUserViewUseCase = saveUserViewUseCase;
        this.saveWholesaleViewUseCase = saveWholesaleViewUseCase;
        this.saveRetailViewUseCase = saveRetailViewUseCase;
        this.updateProductViewUseCase = updateProductViewUseCase;
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

        }
        if(notification.getType().equals("co.com.inventory.model.branch.events.UserRegistered")){
            UserRegistered userRegistered = (UserRegistered) jsonMapper.readFromJson(notification.getBody(), UserRegistered.class);

            saveUserViewUseCase.execute(userRegistered.getAggregateRootId(), userRegistered.getUserName(),
                            userRegistered.getUserLastName(), userRegistered.getUserPassword(),
                            userRegistered.getUserEmail(), userRegistered.getUserRole())
                    .subscribe(branch -> {
                        logger.info(notification.toString());
                    });

        }
        if(notification.getType().equals("co.com.inventory.model.branch.events.ProductSoldWholesale")){
            ProductSoldWholesale productSoldWholesale = (ProductSoldWholesale) jsonMapper.readFromJson(notification.getBody(), ProductSoldWholesale.class);

            List<ProductSale> productSaleList = Mapper.parseJsonToListOfProductSale(productSoldWholesale.getProductSales());

            saveWholesaleViewUseCase.execute(productSoldWholesale.getAggregateRootId(), productSaleList, 0.7F,"Al por mayor","")
                    .subscribe(branch -> {
                        logger.info(notification.toString());
                    });
        }

        if(notification.getType().equals("co.com.inventory.model.branch.events.ProductSoldRetail")){
            ProductSoldRetail productSoldRetail = (ProductSoldRetail) jsonMapper.readFromJson(notification.getBody(), ProductSoldRetail.class);

            List<ProductSale> productSaleList = Mapper.parseJsonToListOfProductSale(productSoldRetail.getProductSales());

            saveRetailViewUseCase.execute(productSoldRetail.getAggregateRootId(), productSaleList, 0.8F,"Al detal","")
                    .subscribe(branch -> {
                        logger.info(notification.toString());
                    });
        }

        if(notification.getType().equals("co.com.inventory.model.branch.events.ProductUpdated")){
            ProductUpdated productUpdated = (ProductUpdated) jsonMapper.readFromJson(notification.getBody(), ProductUpdated.class);


            updateProductViewUseCase.execute(productUpdated.getIdProduct(), productUpdated.getProductInventoryStock())
                    .subscribe(branch -> {
                        logger.info(notification.toString());
                    });
        }
        if(notification.getType().equals("co.com.inventory.model.branch.events.ProductMoved")){
            ProductMoved productMoved = (ProductMoved) jsonMapper.readFromJson(notification.getBody(), ProductMoved.class);

            updateProductViewUseCase.executeMoveBranch(productMoved.getIdProduct(), productMoved.getBranchId())
                    .subscribe(branch -> {
                        logger.info(notification.toString());
                    });
        }


    }


}
