package co.com.inventory.usecase.generic.commands;

import co.com.inventory.usecase.generic.Command;

public class AddProductCommand extends Command {
    private String branchId;
    private String productId;
    private String productName;
    private String productDescription;
    private Float productPrice;
    private Integer productInventoryStock;
    private String productCategory;

    public AddProductCommand() {
    }

    public AddProductCommand(String branchId, String productId, String productName, String productDescription,
                             Float productPrice, Integer productInventoryStock, String productCategory) {
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

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductInventoryStock() {
        return productInventoryStock;
    }

    public void setProductInventoryStock(Integer productInventoryStock) {
        this.productInventoryStock = productInventoryStock;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
