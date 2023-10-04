package co.com.inventory.usecase.registersaleretail;


import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.usecase.generic.UseCaseForCommand;
import co.com.inventory.usecase.alpha.comands.AddProductSaleCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RegisterSaleRetailUseCase{
    private final DomainEventRepository domainEventRepository;
    private final EventBus eventBus;


    public RegisterSaleRetailUseCase(DomainEventRepository domainEventRepository, EventBus eventBus) {
        this.domainEventRepository = domainEventRepository;

        this.eventBus = eventBus;
    }


    public Flux<DomainEvent> apply(Mono<AddProductSaleCommand> addProductSaleCommandMono) {
        return null;
/*        return addProductSaleCommandMono.flatMap(addProductSaleCommand -> {
            String uuid = UUID.randomUUID().toString();
            return mySqlRepository.saveSale(addProductSaleCommand.getBranchId(),
                    addProductSaleCommand.getProductSalesUtil(),uuid, 0.8F);

        }).flatMapMany(wraperSaveProductSales -> {
            return domainEventRepository.findById(wraperSaveProductSales.getBranchId().toString())
                    .collectList()
                    .flatMapMany(events -> {
                        Branch branch = Branch.from(BranchId.of(wraperSaveProductSales.getBranchId().toString()),
                                events);
                        branch.registerSaleRetail(ProductSaleId.of(wraperSaveProductSales.getUuid()),
                                Mapper.parseJsonToList(wraperSaveProductSales.getProductSaleUtilList().toString())

                        );
                        return Flux.fromIterable(branch.getUncommittedChanges())
                                .flatMap(domainEventRepository::saveEvent).map(
                                        domainEvent -> {
                                            eventBus.publish(domainEvent);
                                            return domainEvent;
                                        }
                                );

                    });
        });*/
    }
}
