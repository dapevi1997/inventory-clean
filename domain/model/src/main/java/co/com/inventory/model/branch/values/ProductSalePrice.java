package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

public class ProductSalePrice implements ValueObject<Float> {
    private Float productSalePrice;

    public ProductSalePrice(Float productSalePrice) {
        this.productSalePrice = productSalePrice;
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
