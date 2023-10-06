package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GetPoductsUC {
    private final MySqlRepositoryQuery mySqlRepositoryQuery;

    public GetPoductsUC(MySqlRepositoryQuery mySqlRepositoryQuery) {
        this.mySqlRepositoryQuery = mySqlRepositoryQuery;
    }

   public Flux<Product> execute(){
        return mySqlRepositoryQuery.getAllProducts();
    }
}
