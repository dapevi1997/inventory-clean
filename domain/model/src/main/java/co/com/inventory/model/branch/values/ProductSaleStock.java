package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

public class ProductSaleStock implements ValueObject<Integer> {
    private Integer productSaleStock;

    public ProductSaleStock(Integer productSaleStock) {
        this.productSaleStock = productSaleStock;
    }

    @Override
    public Integer value() {
        return productSaleStock;
    }

    public Integer getProductSaleStock() {
        return productSaleStock;
    }
}
