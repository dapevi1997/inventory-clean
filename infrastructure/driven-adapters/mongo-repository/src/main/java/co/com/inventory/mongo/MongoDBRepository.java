package co.com.inventory.mongo;

import co.com.inventory.model.branch.generic.DomainEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBRepository extends ReactiveMongoRepository<DomainEvent, String> {
}
