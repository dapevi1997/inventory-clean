package co.com.inventory.events;

import co.com.inventory.controller.model.*;
import co.com.inventory.events.data.Notification;
import co.com.inventory.events.utils.Mapper;
import co.com.inventory.mapper.JSONMapper;
import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.events.*;
import co.com.inventory.controller.SocketController;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
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

            ProductAddedModel productAddedModel = new ProductAddedModel();

            productAddedModel.setBranchId(productAdded.getBranchId());
            productAddedModel.setProductId(productAdded.getProductId());
            productAddedModel.setProductName(productAdded.getProductName());
            productAddedModel.setProductDescription(productAdded.getProductDescription());
            productAddedModel.setProductPrice(Float.parseFloat(productAdded.getProductPrice()));
            productAddedModel.setProductInventoryStock(Integer.parseInt(productAdded.getProductInventoryStock()));
            productAddedModel.setProductCategory(productAdded.getProductCategory());


            socketController.sendProductAdded("productAdded", productAddedModel);

            logger.info(notification.toString());
        }
        if(notification.getType().equals("co.com.inventory.model.branch.events.BranchCreated")){
            BranchCreated branchCreated = (BranchCreated) jsonMapper.readFromJson(notification.getBody(), BranchCreated.class);

            BranchAddedModel branchAddedModel = new BranchAddedModel();

            branchAddedModel.setBranchName(branchCreated.getBranchName());
            branchAddedModel.setBranchCountry(branchCreated.getBranchCountry());
            branchAddedModel.setBranchCity(branchCreated.getBranchCity());
            branchAddedModel.setBranchId(branchCreated.getAggregateRootId());


            socketController.sendBranchAdded("branchAdded", branchAddedModel);

            logger.info(notification.toString());
        }
        if(notification.getType().equals("co.com.inventory.model.branch.events.ProductUpdated")){
            ProductUpdated productUpdated = (ProductUpdated) jsonMapper.readFromJson(notification.getBody(), ProductUpdated.class);

            ProductUpdatedModel productUpdatedModel = new ProductUpdatedModel();
            productUpdatedModel.setIdProduct(productUpdated.getIdProduct());
            productUpdatedModel.setProductInventoryStock(productUpdated.getProductInventoryStock());


            socketController.sendProductUpdated("productUpdated", productUpdatedModel);

            logger.info(notification.toString());
        }
        if(notification.getType().equals("co.com.inventory.model.branch.events.ProductMoved")){
            ProductMoved productUpdated = (ProductMoved) jsonMapper.readFromJson(notification.getBody(), ProductMoved.class);

            ProductMovedModel productMovedModel = new ProductMovedModel();
            productMovedModel.setIdProduct(productUpdated.getIdProduct());
            productMovedModel.setBranchId(productUpdated.getBranchId());


            socketController.sendProductMoved("productMoved", productMovedModel);

            logger.info(notification.toString());
        }
        if(notification.getType().equals("co.com.inventory.model.branch.events.ProductSoldWholesale")){
            ProductSoldWholesale productSoldWholesale = (ProductSoldWholesale) jsonMapper.readFromJson(notification.getBody(), ProductSoldWholesale.class);

            List<ProductSale> productSaleList = Mapper.parseJsonToListOfProductSale(productSoldWholesale.getProductSales());

            List<ProductsSaleModel> productsSaleModelList = new ArrayList<>();

            productSaleList.forEach(
                    productSale -> {
                        ProductsSaleModel productsSaleModel = new ProductsSaleModel();
                        productsSaleModel.setProductSaleId(productSale.getProductSaleId().getProductSaleId());
                        productsSaleModel.setProductSaleStock(productSale.getProductSaleStock().getProductSaleStock().toString());

                        productsSaleModelList.add(productsSaleModel);
                    }
            );


            socketController.sendWholeSaleAdded("wholeSale", productsSaleModelList);

            logger.info(notification.toString());
        }
        if(notification.getType().equals("co.com.inventory.model.branch.events.ProductSoldRetail")){
            ProductSoldRetail productSoldRetail = (ProductSoldRetail) jsonMapper.readFromJson(notification.getBody(), ProductSoldRetail.class);

            List<ProductSale> productSaleList = Mapper.parseJsonToListOfProductSale(productSoldRetail.getProductSales());

            List<ProductsSaleModel> productsSaleModelList = new ArrayList<>();

            productSaleList.forEach(
                    productSale -> {
                        ProductsSaleModel productsSaleModel = new ProductsSaleModel();
                        productsSaleModel.setProductSaleId(productSale.getProductSaleId().getProductSaleId());
                        productsSaleModel.setProductSaleStock(productSale.getProductSaleStock().getProductSaleStock().toString());

                        productsSaleModelList.add(productsSaleModel);
                    }
            );


            socketController.sendRetailAdded("retail", productsSaleModelList);

            logger.info(notification.toString());
        }



    }


}
