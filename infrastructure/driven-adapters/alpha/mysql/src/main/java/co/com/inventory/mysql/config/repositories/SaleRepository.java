package co.com.inventory.mysql.config.repositories;

import co.com.inventory.mysql.config.models.BranchMySQL;
import co.com.inventory.mysql.config.models.SaleMySQL;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends R2dbcRepository<SaleMySQL,Long> {
}
