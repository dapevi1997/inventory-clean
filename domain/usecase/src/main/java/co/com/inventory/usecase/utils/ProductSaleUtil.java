package co.com.inventory.usecase.utils;

public class ProductSaleUtil {
    private String productSaleId;
    private String productSalePrice;
    private String productSaleStock;

    public ProductSaleUtil() {
    }

    public ProductSaleUtil(String productSaleId, String productSalePrice, String productSaleStock) {
        this.productSaleId = productSaleId;
        this.productSalePrice = productSalePrice;
        this.productSaleStock = productSaleStock;
    }

    public String getProductSaleId() {
        return productSaleId;
    }

    public void setProductSaleId(String productSaleId) {
        this.productSaleId = productSaleId;
    }

    public String getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(String productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public String getProductSaleStock() {
        return productSaleStock;
    }

    public void setProductSaleStock(String productSaleStock) {
        this.productSaleStock = productSaleStock;
    }
}
