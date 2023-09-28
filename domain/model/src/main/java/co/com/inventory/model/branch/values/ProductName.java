package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

import java.util.Objects;

public class ProductName implements ValueObject<String> {
    private String productName;

    public ProductName(String productName) {
        Objects.requireNonNull(productName);
        this.productName = productName;
    }

    @Override
    public String value() {
        return productName;
    }

    public String getProductName() {
        return productName;
    }
}
