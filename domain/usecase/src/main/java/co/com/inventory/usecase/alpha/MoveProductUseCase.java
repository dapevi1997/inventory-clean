package co.com.inventory.usecase.alpha;


import co.com.inventory.model.branch.events.ProductMoved;
import co.com.inventory.model.branch.events.ProductUpdated;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.model.branch.values.BranchId;
import co.com.inventory.model.branch.values.ProductId;
import co.com.inventory.model.branch.values.ProductInventoryStock;
import co.com.inventory.usecase.alpha.comands.MoveProductCommand;
import co.com.inventory.usecase.alpha.comands.UpdateProductCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Mono;

public class MoveProductUseCase {
    private final DomainEventRepository domainEventRepository;
    private final EventBus eventBus;


    public MoveProductUseCase(DomainEventRepository domainEventRepository, EventBus eventBus) {
        this.domainEventRepository = domainEventRepository;

        this.eventBus = eventBus;
    }

    public Mono<DomainEvent> apply(Mono<MoveProductCommand> moveProductCommandMono) {
        return moveProductCommandMono.flatMap(moveProductCommand -> {
            ProductId.of(moveProductCommand.getIdProduct());
            BranchId.of(moveProductCommand.getBranchId());

            ProductMoved productMoved = new ProductMoved(moveProductCommand.getIdProduct(),
                    moveProductCommand.getBranchId()
            );

            return domainEventRepository.saveEvent(productMoved)
                    .map(domainEvent -> {
                        eventBus.publish(domainEvent);
                        return domainEvent;
                    });
        });

    }
}
