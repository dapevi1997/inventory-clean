package co.com.inventory.model.branch.events;

import co.com.inventory.model.branch.utils.DomainEvent;


public class ProductAdded extends DomainEvent {
    private String branchId;
    private String productId;
    private String productName;
    private String productDescription;
    private String productPrice;
    private String productInventoryStock;
    private String productCategory;
    public ProductAdded() {
        super("co.com.inventory.model.branch.events.ProductAdded","branch");
    }

    public ProductAdded(String branchId, String productId, String productName, String productDescription,
                        String productPrice, String productInventoryStock, String productCategory) {
        super("co.com.inventory.model.branch.events.ProductAdded","branch");
        super.setAggregateRootId(branchId);
        this.branchId = branchId;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productInventoryStock = productInventoryStock;
        this.productCategory = productCategory;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductInventoryStock() {
        return productInventoryStock;
    }

    public String getProductCategory() {
        return productCategory;
    }
}
