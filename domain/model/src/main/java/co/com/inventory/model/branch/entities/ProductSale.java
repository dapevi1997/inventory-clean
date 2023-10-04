package co.com.inventory.model.branch.entities;

import co.com.inventory.model.branch.values.ProductSaleId;
import co.com.inventory.model.branch.values.ProductSalePrice;
import co.com.inventory.model.branch.values.ProductSaleStock;

import java.util.Objects;

public class ProductSale {
    private ProductSaleId productSaleId;
    private ProductSalePrice productSalePrice;
    private ProductSaleStock productSaleStock;


    public ProductSale(ProductSaleId productSaleId, ProductSalePrice productSalePrice ,ProductSaleStock productSaleStock) {
        Objects.requireNonNull(productSaleId, "El ProductSaleId no puede ser nulo");
        this.productSaleId = productSaleId;
        this.productSalePrice = productSalePrice;
        this.productSaleStock = productSaleStock;
    }

    public ProductSaleId getProductSaleId() {
        return productSaleId;
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

}
