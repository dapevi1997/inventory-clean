package co.com.inventory.usecase.beta;

import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import reactor.core.publisher.Mono;

public class GetPriceByIdUC {
    private final MySqlRepositoryQuery mySqlRepositoryQuery;

    public GetPriceByIdUC(MySqlRepositoryQuery mySqlRepositoryQuery) {
        this.mySqlRepositoryQuery = mySqlRepositoryQuery;
    }

   public Mono<Float> execute(String idProduct){
        return mySqlRepositoryQuery.findProductbyId(idProduct);
    }
}
