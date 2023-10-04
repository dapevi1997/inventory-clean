package co.com.inventory.usecase.generic.gateways;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.utils.ProductSaleUtil;
import co.com.inventory.model.branch.utils.WraperSaveProductSales;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MySqlRepository {
    Mono<Branch> saveBranch(String branchId,String branchNameP, String branchCountry, String branchCity);
    Mono<Product> saveProduct(String branchId, String productName, String description, String productPrice,
                              String productInventoryStock, String productCategory);
    Mono<User> saveUser(String branchId, String userName,String userLastName,String userPassword, String userEmail, String userRol);

    Mono<Product> findProductbyId(Long productId);

    Mono<WraperSaveProductSales> saveProductSales(String branchId, List<ProductSaleUtil> productSaleUtilList, String uuid, Float discount);
}
