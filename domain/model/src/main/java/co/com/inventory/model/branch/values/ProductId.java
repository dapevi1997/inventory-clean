package co.com.inventory.model.branch.values;


import co.com.inventory.model.branch.exceptions.BlankStringException;

import java.util.Objects;

public class ProductId {
    private String productId;

    public ProductId() {
    }

    public ProductId(String productId) {
        Objects.requireNonNull(productId, "El campo productId no puede ser nulo");
        if(productId.isBlank()){
            throw new BlankStringException("El campo productId no puede estar vacío");
        }
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public static ProductId of(String uuid){
        return new ProductId(uuid);
    }
}
