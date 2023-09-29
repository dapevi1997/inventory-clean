package co.com.inventory.mysql.config.repositories;

import co.com.inventory.mysql.config.models.Branch;
import co.com.inventory.mysql.config.models.Product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends R2dbcRepository<Product,Long> {
}
