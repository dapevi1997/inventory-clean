package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.Identity;

public class ProductSaleId extends Identity {
    public ProductSaleId() {
    }

    public ProductSaleId(String uuid) {
        super(uuid);
    }
    public static ProductSaleId of(String uuid){
        return new ProductSaleId(uuid);
    }

}
