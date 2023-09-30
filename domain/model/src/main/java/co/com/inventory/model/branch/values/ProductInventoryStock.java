package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

import java.util.Objects;

public class ProductInventoryStock implements ValueObject<Integer> {
    private Integer productInventoryStock;

    public ProductInventoryStock(String productInventoryStock) {
        Objects.requireNonNull(productInventoryStock, "El campo productInventoryStock no puede ser nulo");
        try {
            this.productInventoryStock = Integer.parseInt(productInventoryStock);
        }catch (Exception e){
            throw new NumberFormatException("El campo productPrice debe ser un número entero");
        }

    }

    @Override
    public Integer value() {
        return productInventoryStock;
    }

    public Integer getProductInventoryStock() {
        return productInventoryStock;
    }
}
