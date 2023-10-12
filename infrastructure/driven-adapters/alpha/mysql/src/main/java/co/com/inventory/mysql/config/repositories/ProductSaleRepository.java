package co.com.inventory.mysql.config.repositories;

import co.com.inventory.mysql.config.models.BranchMySQL;
import co.com.inventory.mysql.config.models.ProductSaleMySQL;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSaleRepository extends R2dbcRepository<ProductSaleMySQL,String> {

}
