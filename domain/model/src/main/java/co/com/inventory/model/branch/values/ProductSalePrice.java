package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

import java.util.Objects;

public class ProductSalePrice implements ValueObject<Float> {
    private Float productSalePrice;

    public ProductSalePrice(String productSalePrice) {
        Objects.requireNonNull(productSalePrice, "El campo productSalePrice no puede ser nulo");
        try {
            this.productSalePrice = Float.parseFloat(productSalePrice);
        }catch (Exception e){
            throw new NumberFormatException("El campo productSalePrice debe ser un número entero");
        }


    }

    @Override
    public Float value() {
        return productSalePrice;
    }

    public Float getProductSalePrice() {
        return productSalePrice;
    }

    @Override
    public String toString() {
        return productSalePrice.toString() ;
    }
}
