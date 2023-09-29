package co.com.inventory.usecase.utils;

public class ProductSaleUtil {
    private String productSaleId;
    private String productSaleStock;

    public ProductSaleUtil() {
    }

    public ProductSaleUtil(String productSaleId, String productSaleStock) {
        this.productSaleId = productSaleId;
        this.productSaleStock = productSaleStock;
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
