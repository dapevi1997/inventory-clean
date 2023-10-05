package co.com.inventory.usecase.alpha;


import co.com.inventory.model.branch.events.ProductSoldRetail;
import co.com.inventory.model.branch.events.ProductSoldWholesale;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.model.branch.values.BranchId;
import co.com.inventory.usecase.generic.UseCaseForCommand;
import co.com.inventory.usecase.alpha.comands.AddProductSaleCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class RegisterSaleRetailUseCase{
    private final DomainEventRepository domainEventRepository;
    private final EventBus eventBus;


    public RegisterSaleRetailUseCase(DomainEventRepository domainEventRepository, EventBus eventBus) {
        this.domainEventRepository = domainEventRepository;

        this.eventBus = eventBus;
    }


    public Mono<DomainEvent> apply(Mono<AddProductSaleCommand> addProductSaleCommandMono) {
        return addProductSaleCommandMono
                .flatMap(addProductSaleCommand -> {
                    BranchId.of(addProductSaleCommand.getBranchId());

                    ProductSoldRetail productSoldWholesale = new ProductSoldRetail(
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
