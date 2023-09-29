package co.com.inventory.model.branch.events;

import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.generic.DomainEvent;

import java.util.List;

public class ProductSoldWholesale extends DomainEvent {
    //private String branchId;
    private String productSaleId;

    private String productSales;

    public ProductSoldWholesale() {
        super("co.com.inventory.model.branch.events.ProductSoldWholesale");
    }

    public ProductSoldWholesale(String productSaleId, String productSales) {
        super("co.com.inventory.model.branch.events.ProductSoldWholesale");
        this.productSaleId = productSaleId;
        this.productSales = productSales;
    }

/*    public String getBranchId() {
        return branchId;
    }*/

    public String getProductSales() {
        return productSales;
    }

    public String getProductSaleId() {
        return productSaleId;
    }
}
