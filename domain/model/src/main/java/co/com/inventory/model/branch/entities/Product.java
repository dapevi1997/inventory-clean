package co.com.inventory.model.branch.entities;

import co.com.inventory.model.branch.generic.Entity;
import co.com.inventory.model.branch.values.*;

import java.util.Objects;

public class Product extends Entity<ProductId> {
    private ProductName productName;
    private ProductDescription productDescription;
    private ProductPrice productPrice;
    private ProductInventoryStock productInventoryStock;
    private ProductCategory productCategory;

    public Product(ProductId productId, ProductName productName, ProductDescription productDescription, ProductPrice productPrice, ProductInventoryStock productInventoryStock, ProductCategory productCategory) {
        super(productId);
        Objects.requireNonNull(productId, "El ProductId no puede ser nulo");
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productInventoryStock = productInventoryStock;
        this.productCategory = productCategory;
    }


    public ProductName getProductName() {
        return productName;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public ProductPrice getProductPrice() {
        return productPrice;
    }

    public ProductInventoryStock getProductInventoryStock() {
        return productInventoryStock;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductName(ProductName productName) {
        this.productName = productName;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(ProductPrice productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductInventoryStock(ProductInventoryStock productInventoryStock) {
        this.productInventoryStock = productInventoryStock;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
