package co.com.inventory.usecase.alpha;


import co.com.inventory.model.branch.events.ProductSoldWholesale;
import co.com.inventory.model.branch.events.UserRegistered;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.generic.UseCaseForCommand;
import co.com.inventory.usecase.alpha.comands.AddProductSaleCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class RegisterSaleWholesaleUseCase {
    private final DomainEventRepository domainEventRepository;
    private final EventBus eventBus;


    public RegisterSaleWholesaleUseCase(DomainEventRepository domainEventRepository, EventBus eventBus) {
        this.domainEventRepository = domainEventRepository;
        this.eventBus = eventBus;
    }

    public Mono<DomainEvent> apply(Mono<AddProductSaleCommand> addProductSaleCommandMono) {
        return addProductSaleCommandMono
                .flatMap(addProductSaleCommand -> {
            BranchId.of(addProductSaleCommand.getBranchId());

            ProductSoldWholesale productSoldWholesale = new ProductSoldWholesale(
                    addProductSaleCommand.getBranchId(),
                    UUID.randomUUID().toString(),
                    addProductSaleCommand.getProductSalesUtil().toString()
            );

            return domainEventRepository.saveEvent(productSoldWholesale)
                    .map(domainEvent -> {
                        eventBus.publish(domainEvent);
                        return domainEvent;
                    });
        });


    }
}
