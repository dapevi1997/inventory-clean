package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

import java.util.Objects;

public class ProductPrice implements ValueObject<Float> {
    private Float productPrice;

    public ProductPrice(String productPrice) {
        Objects.requireNonNull(productPrice,"El campo productPrice no puede ser nulo");
        try {
            this.productPrice = Float.parseFloat(productPrice);
        }catch (Exception e){
            throw new NumberFormatException("El campo productPrice debe ser un número");
        }

    }

    @Override
    public Float value() {
        return productPrice;
    }

    public Float getProductPrice() {
        return productPrice;
    }
}
