package co.com.inventory.mysql.config.repositories;

import co.com.inventory.mysql.config.models.ProductMySQL;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends R2dbcRepository<ProductMySQL,String> {
}
