package co.com.inventory.model.branch.events;

import co.com.inventory.model.branch.generic.DomainEvent;

public class ProductSoldRetail extends DomainEvent {
    private Float productSoldPrice;
    private Integer productStock;

    public ProductSoldRetail() {
        super("co.com.inventory.model.branch.events.ProductSoldRetail");
    }

    public ProductSoldRetail(Float productSoldPrice, Integer productStock) {
        super("co.com.inventory.model.branch.events.ProductSoldRetail");
        this.productSoldPrice = productSoldPrice;
        this.productStock = productStock;
    }

    public Float getProductSoldPrice() {
        return productSoldPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }
}
