package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

import java.util.Objects;

public class ProductInventoryStock implements ValueObject<Integer> {
    private Integer productInventoryStock;

    public ProductInventoryStock(Integer productInventoryStock) {
        Objects.requireNonNull(productInventoryStock);
        this.productInventoryStock = productInventoryStock;
    }

    @Override
    public Integer value() {
        return productInventoryStock;
    }

    public Integer getProductInventoryStock() {
        return productInventoryStock;
    }
}
