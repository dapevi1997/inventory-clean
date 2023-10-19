package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import reactor.core.publisher.Mono;

public class SaveBranchViewUC {
    private final MySqlRepositoryQuery mySqlRepositoryQuery;
    public SaveBranchViewUC(MySqlRepositoryQuery mySqlRepositoryQuery) {

        this.mySqlRepositoryQuery = mySqlRepositoryQuery;
    }
    public Mono<Branch> execute(String branchId, String branchName, String branchCountry, String branchCity){
        return mySqlRepositoryQuery.saveBranch(branchId, branchName,
                branchCountry, branchCity);
    }
}
