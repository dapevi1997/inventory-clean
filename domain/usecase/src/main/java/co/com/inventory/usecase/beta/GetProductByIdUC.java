package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import reactor.core.publisher.Mono;

public class GetProductByIdUC {
    private final MySqlRepositoryQuery mySqlRepositoryQuery;

    public GetProductByIdUC(MySqlRepositoryQuery mySqlRepositoryQuery) {
        this.mySqlRepositoryQuery = mySqlRepositoryQuery;
    }

   public Mono<Product> execute(String idProduct){
        return mySqlRepositoryQuery.findProductbyId(idProduct);
    }
}
