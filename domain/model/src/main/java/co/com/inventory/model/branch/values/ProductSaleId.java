package co.com.inventory.model.branch.values;


import java.util.Objects;

public class ProductSaleId{
    private Long ProductSaleId;
    public ProductSaleId(String productSaleId) {
        Objects.requireNonNull(productSaleId, "El campo ProductSaleId no puede ser nulo");
        try {
            this.ProductSaleId = Long.parseLong(productSaleId);
        }catch (Exception e){
            throw new NumberFormatException("El campo ProductSaleId debe ser un número entero");
        }
    }

    public Long getProductSaleId() {
        return ProductSaleId;
    }

    public void setProductSaleId(Long productSaleId) {
        ProductSaleId = productSaleId;
    }

    public static ProductSaleId of(String uuid){
        return new ProductSaleId(uuid);
    }

}
