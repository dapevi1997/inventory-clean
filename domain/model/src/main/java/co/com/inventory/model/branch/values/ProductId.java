package co.com.inventory.model.branch.values;


import java.util.Objects;

public class ProductId {
    private Long productId;
    public ProductId(String productId) {
        Objects.requireNonNull(productId, "El campo productId no puede ser nulo");
        try {
            this.productId = Long.parseLong(productId);
        }catch (Exception e){
            throw new NumberFormatException("El campo productId debe ser un número entero");
        }
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public static ProductId of(String uuid){
        return new ProductId(uuid);
    }
}
