package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import reactor.core.publisher.Flux;

public class GetBranchsUC {
    private final MySqlRepositoryQuery mySqlRepositoryQuery;

    public GetBranchsUC(MySqlRepositoryQuery mySqlRepositoryQuery) {
        this.mySqlRepositoryQuery = mySqlRepositoryQuery;
    }

   public Flux<Branch> execute(){
        return mySqlRepositoryQuery.getAllBranchs();
    }
}
