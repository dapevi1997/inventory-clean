package co.com.inventory.usecase.generic.gateways;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.User;
import reactor.core.publisher.Mono;

public interface MySqlRepository {
    Mono<Branch> saveBranch(String branchNameP, String branchCountry, String branchCity);
    Mono<Product> saveProduct(String branchId, String productName, String description, String productPrice,
                              String productInventoryStock, String productCategory);
    Mono<User> saveUser(String branchId, String userName,String userLastName,String userPassword, String userEmail, String userRol);
}
