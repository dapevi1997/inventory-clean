package co.com.inventory.usecase.generic.gateways;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.values.BranchId;
import reactor.core.publisher.Mono;

public interface MySqlRepository {
    Mono<Branch> saveBranch(String branchName, String branchLocation);
}
