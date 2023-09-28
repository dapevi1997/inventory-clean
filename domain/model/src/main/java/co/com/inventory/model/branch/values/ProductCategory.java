package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

import java.util.Objects;

public class ProductCategory implements ValueObject<String> {
    private String productCategory;

    public ProductCategory(String productCategory) {
        Objects.requireNonNull(productCategory);
        this.productCategory = productCategory;
    }

    @Override
    public String value() {
        return productCategory;
    }

    public String getProductCategory() {
        return productCategory;
    }
}
