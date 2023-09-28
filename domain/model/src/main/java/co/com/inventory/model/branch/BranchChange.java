package co.com.inventory.model.branch;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.model.branch.events.ProductAdded;
import co.com.inventory.model.branch.generic.EventChange;
import co.com.inventory.model.branch.values.*;

import java.util.HashSet;

public class BranchChange extends EventChange {
    public BranchChange(Branch branch) {
        apply(
                (BranchCreated event) -> {
                    //TODO: terminar de implementar
                    branch.branchName = new BranchName(event.getBranchName());
                    branch.branchLocation = new BranchLocation(event.getBranchLocation());
                }
        );
        apply(
                (ProductAdded event)->{
                    Product product = new Product(ProductId.of(event.getProductId()), new ProductName(event.getProductName()),
                            new ProductDescription(event.getProductDescription()), new ProductPrice(event.getProductPrice()),
                            new ProductInventoryStock(event.getProductInventoryStock()),new ProductCategory(event.getProductCategory())
                    );

                    branch.products = new HashSet<>();
                    branch.products.add(product);
                }
        );
    }
}
