package co.com.inventory.mysql.config;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.values.BranchId;
import co.com.inventory.model.branch.values.BranchLocation;
import co.com.inventory.model.branch.values.BranchName;
import co.com.inventory.mysql.config.models.BranchMySQL;
import co.com.inventory.mysql.config.repositories.BranchRepository;
import co.com.inventory.mysql.config.utilities.MapperUtils;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MySQLAdapter implements MySqlRepository {

    private final BranchRepository branchRepository;
    private final MapperUtils mapperUtils;

    public MySQLAdapter(BranchRepository branchRepository, MapperUtils mapperUtils) {
        this.branchRepository = branchRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<Branch> saveBranch(String branchNameP, String branchCountry, String branchCity) {
        BranchMySQL branchMySQLAux = new BranchMySQL();
        BranchName branchName = new BranchName(branchNameP);
        BranchLocation branchLocation = new BranchLocation(branchCountry,branchCity);

        branchMySQLAux.setBranch_country(branchCountry);
        branchMySQLAux.setBranchCity(branchCity);

        return branchRepository.save(branchMySQLAux).map(branchMySQL -> {
            return new Branch(BranchId.of(branchMySQL.getId().toString()),
                    branchName,branchLocation) ;
        });

    }

}
