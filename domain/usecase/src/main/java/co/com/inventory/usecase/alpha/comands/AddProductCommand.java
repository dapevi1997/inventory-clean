package co.com.inventory.usecase.alpha.comands;

public class AddProductCommand{
    private String branchId;
    private String productName;
    private String productDescription;
    private String productPrice;
    private String productInventoryStock;
    private String productCategory;

    public AddProductCommand() {
    }

    public AddProductCommand(String branchId, String productName, String productDescription,
                             String productPrice, String productInventoryStock, String productCategory) {
        this.branchId = branchId;
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

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductInventoryStock() {
        return productInventoryStock;
    }

    public void setProductInventoryStock(String productInventoryStock) {
        this.productInventoryStock = productInventoryStock;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
