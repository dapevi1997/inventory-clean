package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import reactor.core.publisher.Mono;

public class SaveProductViewUC {
    private final MySqlRepositoryQuery mySqlRepositoryQuery;
    public SaveProductViewUC(MySqlRepositoryQuery mySqlRepositoryQuery) {

        this.mySqlRepositoryQuery = mySqlRepositoryQuery;
    }
    public Mono<Product> execute(String branchId,
                                 String productId,
                                 String productName,
                                 String description,
                                 String productPrice,
                                 String productInventoryStock,
                                 String productCategory){
        return mySqlRepositoryQuery.saveProduct( branchId, productId,
                productName,
                 description,
                 productPrice,
                 productInventoryStock,
                 productCategory);
    }
}
