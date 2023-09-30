package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.exceptions.BlankStringException;
import co.com.inventory.model.branch.generic.ValueObject;

import java.util.Objects;

public class ProductName implements ValueObject<String> {
    private String productName;

    public ProductName(String productName) {
        Objects.requireNonNull(productName, "El campo productName no puede ser nulo");
        if(productName.isBlank()){
            throw new BlankStringException("El campo productName no puede estar vacío");
        }

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
