package co.com.inventory.model.branch.events;

import co.com.inventory.model.branch.utils.DomainEvent;

public class ProductUpdated extends DomainEvent {
    private String idProduct;
    private Integer productInventoryStock;

    public ProductUpdated() {
        super("co.com.inventory.model.branch.events.ProductUpdated","branch");
    }

    public ProductUpdated(String idProduct, Integer productInventoryStock) {
        super("co.com.inventory.model.branch.events.ProductUpdated", "branch");

        this.idProduct = idProduct;
        this.productInventoryStock = productInventoryStock;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public Integer getProductInventoryStock() {
        return productInventoryStock;
    }
}
