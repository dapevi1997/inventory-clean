package co.com.inventory.model.branch.events;

import co.com.inventory.model.branch.utils.DomainEvent;

public class ProductMoved extends DomainEvent {
    private String idProduct;
    private String branchId;

    public ProductMoved() {
        super("co.com.inventory.model.branch.events.ProductMoved","branch");
    }

    public ProductMoved(String idProduct, String branchId) {
        super("co.com.inventory.model.branch.events.ProductMoved", "branch");

        this.idProduct = idProduct;
        this.branchId = branchId;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public String getBranchId() {
        return branchId;
    }
}
