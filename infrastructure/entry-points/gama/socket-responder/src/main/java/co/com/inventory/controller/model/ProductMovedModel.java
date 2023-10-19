package co.com.inventory.controller.model;

public class ProductMovedModel {
    private String idProduct;
    private String branchId;

    public ProductMovedModel() {
    }

    public ProductMovedModel(String idProduct, String branchId) {
        this.idProduct = idProduct;
        this.branchId = branchId;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
