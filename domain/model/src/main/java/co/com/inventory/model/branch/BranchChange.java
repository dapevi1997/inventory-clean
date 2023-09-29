package co.com.inventory.model.branch;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.model.branch.events.ProductAdded;
import co.com.inventory.model.branch.events.ProductSoldWholesale;
import co.com.inventory.model.branch.events.UserRegistered;
import co.com.inventory.model.branch.generic.EventChange;
import co.com.inventory.model.branch.utils.Mapper;
import co.com.inventory.model.branch.values.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
        apply(
                (UserRegistered event) -> {
                    User user = new User(UserId.of(event.getUserId()), new UserName(event.getUserName()),
                            new UserPassword(event.getUserPassword()), new UserEmail(event.getUserEmail()),
                            new UserRole(event.getUserRole()));
                    branch.users = new HashSet<>();
                    branch.users.add(user);
                }
        );
        apply(
                (ProductSoldWholesale event)->{

                    String productSalesJsonString = event.getProductSales().toString();
                    List<ProductSale> productSaleList = Mapper.parseJsonToList(productSalesJsonString);

                    branch.productSales = productSaleList;

                    productSaleList.forEach(productSale -> {

                        Optional<Product> productFound = branch.products.stream()
                                .filter(product -> product.identity().value() == productSale.identity().value())
                                .map(product -> {
                                    product.setProductInventoryStock(new ProductInventoryStock(product.getProductInventoryStock().value() - productSale.getProductSaleStock().value()));
                                    return product;
                                }).findFirst();
                    });



                }
        );
    }
}
