package co.com.inventory.model.branch.utils;

public class ProductSaleUtil {
    private String productSaleId;
    private String productSaleStock;
    private String productSalePrice = "";

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

    public String getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(String productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    @Override
    public String toString() {
        return "{" +
                "productSaleId:" + productSaleId  +
                ", productSalePrice:" + productSalePrice  +
                ", productSaleStock:" + productSaleStock +
                '}';
    }
}
