package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.utils.SalesByBranchDTOModel;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import reactor.core.publisher.Flux;

public class GetSalesByBranchIdUC {
    private final MySqlRepositoryQuery mySqlRepositoryQuery;

    public GetSalesByBranchIdUC(MySqlRepositoryQuery mySqlRepositoryQuery) {
        this.mySqlRepositoryQuery = mySqlRepositoryQuery;
    }

   public Flux<SalesByBranchDTOModel> execute(String branchId){
        return mySqlRepositoryQuery.getSalesByBranchId(branchId);
    }
}
