package co.com.inventory.mysql.config.repositories;

import co.com.inventory.mysql.config.models.UserMySQL;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<UserMySQL,String> {
    Mono<UserMySQL> findByUserEmail(String userEmail);
}
