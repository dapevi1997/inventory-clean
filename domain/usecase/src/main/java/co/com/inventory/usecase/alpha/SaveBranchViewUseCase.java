package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import reactor.core.publisher.Mono;

public class SaveBranchViewUseCase {
    private final MySqlRepository mySqlRepository;
    public SaveBranchViewUseCase(MySqlRepository mySqlRepository) {
        this.mySqlRepository = mySqlRepository;
    }
    public Mono<Branch> execute(String branchId, String branchName, String branchCountry, String branchCity){
        return mySqlRepository.saveBranch(branchId, branchName,
                branchCountry, branchCity);
    }
}
