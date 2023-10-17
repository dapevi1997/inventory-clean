package co.com.inventory.controller.model;

public class WholeSaleAddedModel {
    private String branchId;
    private String productSaleId;
    private String productSales;

    public WholeSaleAddedModel() {
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getProductSaleId() {
        return productSaleId;
    }

    public void setProductSaleId(String productSaleId) {
        this.productSaleId = productSaleId;
    }

    public String getProductSales() {
        return productSales;
    }

    public void setProductSales(String productSales) {
        this.productSales = productSales;
    }
}
