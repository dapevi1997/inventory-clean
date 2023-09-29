package co.com.inventory.model.branch.events;

import co.com.inventory.model.branch.generic.DomainEvent;

public class ProductSoldRetail extends DomainEvent {
    private String productSaleId;

    private String productSales;

    public ProductSoldRetail() {
        super("co.com.inventory.model.branch.events.ProductSoldRetail");
    }

    public ProductSoldRetail(String productSaleId, String productSales) {
        super("co.com.inventory.model.branch.events.ProductSoldRetail");
        this.productSaleId = productSaleId;
        this.productSales = productSales;
    }

    public String getProductSaleId() {
        return productSaleId;
    }

    public String getProductSales() {
        return productSales;
    }
}
