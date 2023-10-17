package co.com.inventory.model.branch.utils;

public class SalesByBranchDTOModel {
    String branchName ;
    String productName ;
    Float productPrice;
    Float productSalePrice;
    Integer productSaleAmount ;
    String saleDate;
    String saleType;
    String saleUser;

    public SalesByBranchDTOModel() {
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public String getSaleUser() {
        return saleUser;
    }

    public void setSaleUser(String saleUser) {
        this.saleUser = saleUser;
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
