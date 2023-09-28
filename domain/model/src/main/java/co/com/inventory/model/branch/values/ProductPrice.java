package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

import java.util.Objects;

public class ProductPrice implements ValueObject<Float> {
    private Float productPrice;

    public ProductPrice(Float productPrice) {
        Objects.requireNonNull(productPrice);
        this.productPrice = productPrice;
    }

    @Override
    public Float value() {
        return productPrice;
    }

    public Float getProductPrice() {
        return productPrice;
    }
}
