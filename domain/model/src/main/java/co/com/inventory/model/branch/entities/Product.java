package co.com.inventory.model.branch.entities;

import co.com.inventory.model.branch.generic.Entity;
import co.com.inventory.model.branch.values.*;

public class Product extends Entity<ProductId> {
    private ProductName productName;
    private ProductDescription productDescription;
    private ProductPrice productPrice;
    private ProductInventoryStock productInventoryStock;
    private ProductCategory productCategory;

    public Product(ProductId productId, ProductName productName, ProductDescription productDescription, ProductPrice productPrice, ProductInventoryStock productInventoryStock, ProductCategory productCategory) {
        super(productId);
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
}
