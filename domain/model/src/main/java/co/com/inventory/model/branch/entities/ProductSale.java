package co.com.inventory.model.branch.entities;

import co.com.inventory.model.branch.generic.Entity;
import co.com.inventory.model.branch.values.ProductSaleId;
import co.com.inventory.model.branch.values.ProductSalePrice;
import co.com.inventory.model.branch.values.ProductSaleStock;

public class ProductSale extends Entity<ProductSaleId> {
    private ProductSalePrice productSalePrice;
    private ProductSaleStock productSaleStock;


    public ProductSale(ProductSaleId id, ProductSalePrice productSalePrice ,ProductSaleStock productSaleStock) {
        super(id);
        this.productSalePrice = productSalePrice;
        this.productSaleStock = productSaleStock;
    }

    public void setProductSalePrice(ProductSalePrice productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public ProductSalePrice getProductSalePrice() {
        return productSalePrice;
    }

    public ProductSaleStock getProductSaleStock() {
        return productSaleStock;
    }

    @Override
    public String toString() {
        return "{" +
                "productSaleId"+ identity().toString()+
                "productSalePrice:" + productSalePrice +
                ", productSaleStock:" + productSaleStock +
                '}';
    }
}
