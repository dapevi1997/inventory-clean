package co.com.inventory.usecase.alpha;


import co.com.inventory.model.branch.events.ProductAdded;
import co.com.inventory.model.branch.events.ProductUpdated;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.alpha.comands.AddProductCommand;
import co.com.inventory.usecase.alpha.comands.UpdateProductCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class UpdateProductUseCase {
    private final DomainEventRepository domainEventRepository;
    private final EventBus eventBus;


    public UpdateProductUseCase(DomainEventRepository domainEventRepository, EventBus eventBus) {
        this.domainEventRepository = domainEventRepository;

        this.eventBus = eventBus;
    }

    public Mono<DomainEvent> apply(Mono<UpdateProductCommand> updateProductCommandMono) {
        return updateProductCommandMono.flatMap(updateProductCommand -> {
            ProductId.of(updateProductCommand.getIdProduct());
            new ProductInventoryStock(updateProductCommand.getProductInventoryStock().toString());

            ProductUpdated productUpdated = new ProductUpdated(updateProductCommand.getIdProduct(),
                    updateProductCommand.getProductInventoryStock()
            );

            return domainEventRepository.saveEvent(productUpdated)
                    .map(domainEvent -> {
                        eventBus.publish(domainEvent);
                        return domainEvent;
                    });
        });

    }
}
