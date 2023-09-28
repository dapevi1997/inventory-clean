package co.com.inventory.mongo;

import co.com.inventory.mapper.JSONMapper;
import co.com.inventory.model.branch.generic.DomainEvent;
import co.com.inventory.mongo.data.StoredEvent;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Date;

@Repository
public class MongoRepositoryAdapter implements DomainEventRepository
{
    private final ReactiveMongoTemplate mongoRepository;
    private final JSONMapper eventSerializer;

    public MongoRepositoryAdapter(ReactiveMongoTemplate mongoRepository, JSONMapper eventSerializer) {
        this.mongoRepository = mongoRepository;
        this.eventSerializer = eventSerializer;
    }

    @Override
    public Mono<DomainEvent> saveEvent(DomainEvent event) {
        StoredEvent eventStored = new StoredEvent();
        eventStored.setAggregateRootId(event.aggregateRootId());
        eventStored.setTypeName(event.getClass().getTypeName());
        eventStored.setOccurredOn(new Date());
        eventStored.setEventBody(StoredEvent.wrapEvent(event, eventSerializer));

        return mongoRepository.save(eventStored)
                .map(storeEvent -> storeEvent.deserializeEvent(eventSerializer));
    }
}
