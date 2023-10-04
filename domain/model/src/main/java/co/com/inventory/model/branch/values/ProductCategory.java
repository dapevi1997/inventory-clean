package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.exceptions.BlankStringException;

import java.util.Objects;

public class ProductCategory {
    private String productCategory;

    public ProductCategory(String productCategory) {
        Objects.requireNonNull(productCategory, "El campo productCategory no puede ser nulo");
        if(productCategory.isBlank()){
            throw new BlankStringException("El campo productCategory no puede estar vacío");
        }
        this.productCategory = productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    public String getProductCategory() {
        return productCategory;
    }
}
