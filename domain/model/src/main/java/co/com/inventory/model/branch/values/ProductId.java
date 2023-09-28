package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.Identity;

public class ProductId extends Identity {
    public ProductId() {
    }

    public ProductId(String uuid) {
        super(uuid);
    }
    public static ProductId of(String uuid){
        return new ProductId(uuid);
    }
}
