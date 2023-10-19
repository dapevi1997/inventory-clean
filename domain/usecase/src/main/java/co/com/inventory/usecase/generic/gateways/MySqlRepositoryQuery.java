package co.com.inventory.usecase.generic.gateways;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.utils.SalesByBranchDTOModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MySqlRepositoryQuery {
    Mono<Product> findProductbyId(String productId);
    Flux<Product> getAllProducts();
    Flux<Branch> getAllBranchs();
    Flux<SalesByBranchDTOModel> getSalesByBranchId(String idBranch);
    Mono<User> findUserByEmail(String email);

    Mono<Branch> saveBranch(String branchId,String branchNameP, String branchCountry, String branchCity);
    Mono<Product> saveProduct(String branchId,String productId ,String productName, String description, String productPrice,
                              String productInventoryStock, String productCategory);
    Mono<User> saveUser(String branchId, String userName,String userLastName,String userPassword, String userEmail, String userRol);

    Flux<ProductSale> saveSale(String branchId, List<ProductSale> productSaleList, Float discount, String type, String user);
    Mono<Product> updateProductStock(String idProduct, Integer productInventoryStock);
    Mono<Product> updateProductBranch(String idProduct, String branch);
}
