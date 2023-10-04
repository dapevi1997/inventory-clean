package co.com.inventory.usecase.generic.gateways;


import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.model.branch.utils.StoredEventModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainEventRepository {
    Mono<DomainEvent> saveEvent(DomainEvent event);
    Flux<DomainEvent> findById(String aggregateId);
}
