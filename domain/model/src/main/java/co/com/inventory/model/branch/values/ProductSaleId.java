package co.com.inventory.model.branch.values;


import co.com.inventory.model.branch.exceptions.BlankStringException;

import java.util.Objects;

public class ProductSaleId{
    private String productSaleId;
    public ProductSaleId(String productSaleId) {
        Objects.requireNonNull(productSaleId, "El campo productSaleId no puede ser nulo");
        if(productSaleId.isBlank()){
            throw new BlankStringException("El campo productSaleId no puede estar vacío");
        }
        this.productSaleId = productSaleId;
    }

    public String getProductSaleId() {
        return productSaleId;
    }

    public void setProductSaleId(String productSaleId) {
        this.productSaleId = productSaleId;
    }

    public static ProductSaleId of(String uuid){
        return new ProductSaleId(uuid);
    }

}
