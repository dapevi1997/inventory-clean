package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.exceptions.BlankStringException;

import java.util.Objects;

public class ProductName {
    private String productName;

    public ProductName() {
    }

    public ProductName(String productName) {
        Objects.requireNonNull(productName, "El campo productName no puede ser nulo");
        if(productName.isBlank()){
            throw new BlankStringException("El campo productName no puede estar vacío");
        }

        this.productName = productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
}
