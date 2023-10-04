package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.exceptions.BlankStringException;

import java.util.Objects;

public class ProductDescription {
    private String productDescription;

    public ProductDescription(String productDescription) {
        Objects.requireNonNull(productDescription, "El campo productDescription no puede ser nulo");
        if(productDescription.isBlank()){
            throw new BlankStringException("El campo productDescription no puede estar vacío");
        }
        this.productDescription = productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductDescription() {
        return productDescription;
    }
}
