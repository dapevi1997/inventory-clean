package co.com.inventory.mysql.config.repositories;


import co.com.inventory.mysql.config.models.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends R2dbcRepository<User,Long> {
}
