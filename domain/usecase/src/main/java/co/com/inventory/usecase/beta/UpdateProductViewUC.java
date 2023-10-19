package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import reactor.core.publisher.Mono;

public class UpdateProductViewUC {
    private final MySqlRepositoryQuery mySqlRepositoryQuery;
    public UpdateProductViewUC(MySqlRepositoryQuery mySqlRepositoryQuery) {

        this.mySqlRepositoryQuery = mySqlRepositoryQuery;
    }
    public Mono<Product> execute(String idProduct, Integer productInventoryStock
                          ){
        return mySqlRepositoryQuery.updateProductStock( idProduct, productInventoryStock
            );
    }

    public Mono<Product> executeMoveBranch(String idProduct, String idBranch
    ){
        return mySqlRepositoryQuery.updateProductBranch( idProduct, idBranch
        );
    }
}
