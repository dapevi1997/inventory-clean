package co.com.inventory.usecase.generic.gateways;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.utils.SalesByBranchDTOModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MySqlRepositoryQuery {
    Mono<Product> findProductbyId(String productId);
    Flux<Product> getAllProducts();
    Flux<Branch> getAllBranchs();
    Flux<SalesByBranchDTOModel> getSalesByBranchId(String idBranch);
    Mono<User> findUserByEmail(String email);
}
