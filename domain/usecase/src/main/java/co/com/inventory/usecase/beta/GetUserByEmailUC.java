package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import reactor.core.publisher.Mono;

public class GetUserByEmailUC {
    private final MySqlRepositoryQuery mySqlRepositoryQuery;

    public GetUserByEmailUC(MySqlRepositoryQuery mySqlRepositoryQuery) {
        this.mySqlRepositoryQuery = mySqlRepositoryQuery;
    }

   public Mono<User> execute(String userEmail){
        return mySqlRepositoryQuery.findUserByEmail(userEmail);
    }
}
