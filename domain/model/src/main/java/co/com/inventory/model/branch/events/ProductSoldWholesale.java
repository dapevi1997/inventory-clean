package co.com.inventory.model.branch.events;

import co.com.inventory.model.branch.generic.DomainEvent;

public class ProductSoldWholesale extends DomainEvent {
    private Float productSoldPrice;
    private Integer productStock;

    public ProductSoldWholesale() {
        super("co.com.inventory.model.branch.events.ProductSoldWholesale");
    }

    public ProductSoldWholesale(Float productSoldPrice, Integer productStock) {
        super("co.com.inventory.model.branch.events.ProductSoldWholesale");
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
