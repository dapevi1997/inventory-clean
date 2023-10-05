package co.com.inventory.model.branch.events;

import co.com.inventory.model.branch.utils.DomainEvent;

public class ProductSoldRetail extends DomainEvent {
    private String branchId;
    private String productSaleId;

    private String productSales;

    public ProductSoldRetail() {
        super("co.com.inventory.model.branch.events.ProductSoldRetail","branch");
    }

    public ProductSoldRetail(String branchId, String productSaleId, String productSales) {
        super("co.com.inventory.model.branch.events.ProductSoldRetail", "branch");
        this.branchId = branchId;
        this.productSaleId = productSaleId;
        this.productSales = productSales;
        super.setAggregateRootId(branchId);
    }

    public String getBranchId() {
        return branchId;
    }

    public String getProductSaleId() {
        return productSaleId;
    }

    public String getProductSales() {
        return productSales;
    }
}
