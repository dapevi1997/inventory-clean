package co.com.inventory.mysql.config.dtos;

public class SalesByBranchDTO {
    String branchName ;
    String productName ;
    Float productPrice;
    Float productSalePrice;
    Integer productSaleAmount ;

    public SalesByBranchDTO() {
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Float getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(Float productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public Integer getProductSaleAmount() {
        return productSaleAmount;
    }

    public void setProductSaleAmount(Integer productSaleAmount) {
        this.productSaleAmount = productSaleAmount;
    }
}
