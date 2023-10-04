package co.com.inventory.usecase.alpha;


import co.com.inventory.model.branch.events.ProductAdded;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.alpha.comands.AddProductCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class AddProductUseCase {
    private final DomainEventRepository domainEventRepository;
    private final EventBus eventBus;


    public AddProductUseCase(DomainEventRepository domainEventRepository, EventBus eventBus) {
        this.domainEventRepository = domainEventRepository;

        this.eventBus = eventBus;
    }

    public Mono<DomainEvent> apply(Mono<AddProductCommand> addProductCommandMono) {
        return addProductCommandMono.flatMap(addProductCommand -> {
            BranchId.of(addProductCommand.getBranchId());
                    ProductId.of(UUID.randomUUID().toString());
                    new ProductName(addProductCommand.getProductName());
                    new ProductDescription(addProductCommand.getProductDescription());
                    new ProductPrice(addProductCommand.getProductPrice());
                    new ProductInventoryStock(addProductCommand.getProductInventoryStock());
                    new ProductCategory(addProductCommand.getProductCategory());

            ProductAdded productAdded = new ProductAdded(
                    addProductCommand.getBranchId(),
                    UUID.randomUUID().toString(),
                    addProductCommand.getProductName(),
                    addProductCommand.getProductDescription(),
                    addProductCommand.getProductPrice(),
                    addProductCommand.getProductInventoryStock(),
                    addProductCommand.getProductCategory()
            );

            return domainEventRepository.saveEvent(productAdded)
                    .map(domainEvent -> {
                        eventBus.publish(domainEvent);
                        return domainEvent;
                    });
        });

    }
}
