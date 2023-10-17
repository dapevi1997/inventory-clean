package co.com.inventory.controller.model;

public class ProductsSaleModel {
    private String productSaleId;
    private String productSaleStock;

    public ProductsSaleModel() {
    }

    public String getProductSaleId() {
        return productSaleId;
    }

    public void setProductSaleId(String productSaleId) {
        this.productSaleId = productSaleId;
    }

    public String getProductSaleStock() {
        return productSaleStock;
    }

    public void setProductSaleStock(String productSaleStock) {
        this.productSaleStock = productSaleStock;
    }
}
