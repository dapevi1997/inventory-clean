package co.com.inventory.model.branch.events;

import co.com.inventory.model.branch.utils.DomainEvent;

public class ProductSoldWholesale extends DomainEvent {
    private String branchId;
    private String productSaleId;

    private String productSales;

    public ProductSoldWholesale() {
        super("co.com.inventory.model.branch.events.ProductSoldWholesale","branch");
    }

    public ProductSoldWholesale(String branchId, String productSaleId, String productSales) {
        super("co.com.inventory.model.branch.events.ProductSoldWholesale","branch");
        this.branchId = branchId;
        this.productSaleId = productSaleId;
        this.productSales = productSales;
        super.setAggregateRootId(branchId);
    }

    public String getBranchId() {
        return branchId;
    }

    public String getProductSales() {
        return productSales;
    }

    public String getProductSaleId() {
        return productSaleId;
    }
}
