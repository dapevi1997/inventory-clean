package co.com.inventory.usecase.registersalewholesale;


import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.usecase.generic.UseCaseForCommand;
import co.com.inventory.usecase.generic.commands.AddProductSaleCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RegisterSaleWholesaleUseCase extends UseCaseForCommand<AddProductSaleCommand> {
    private final DomainEventRepository domainEventRepository;
    private final EventBus eventBus;


    public RegisterSaleWholesaleUseCase(DomainEventRepository domainEventRepository, EventBus eventBus) {
        this.domainEventRepository = domainEventRepository;
        this.eventBus = eventBus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AddProductSaleCommand> addProductSaleCommandMono) {
        return null;

/*        return addProductSaleCommandMono.flatMap(addProductSaleCommand -> {
            String uuid = UUID.randomUUID().toString();
            return mySqlRepository.saveProductSales(addProductSaleCommand.getBranchId(),
                    addProductSaleCommand.getProductSalesUtil(),uuid,0.7F);

        }).flatMapMany(wraperSaveProductSales -> {
            return domainEventRepository.findById(wraperSaveProductSales.getBranchId().toString())
                    .collectList()
                    .flatMapMany(events -> {
                        Branch branch = Branch.from(BranchId.of(wraperSaveProductSales.getBranchId().toString()),
                                events);
                        branch.registerSaleWholesale(ProductSaleId.of(wraperSaveProductSales.getUuid()),
                                Mapper.parseJsonToList(wraperSaveProductSales.getProductSaleUtilList().toString())

                        );
                        return Flux.fromIterable(branch.getUncommittedChanges())
                                .flatMap(domainEventRepository::saveEvent).map(domainEvent -> {
                                    eventBus.publish(domainEvent);
                                    return domainEvent;
                                });

                    });
        });*/

    }
}
