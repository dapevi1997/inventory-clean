package co.com.inventory.usecase.generic.gateways;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import reactor.core.publisher.Mono;

public interface MySqlRepository {
    Mono<Branch> saveBranch(String branchNameP, String branchCountry, String branchCity);
    Mono<Product> saveProduct(String branchId, String productName, String description, String productPrice,
                              String productInventoryStock, String productCategory);
}
