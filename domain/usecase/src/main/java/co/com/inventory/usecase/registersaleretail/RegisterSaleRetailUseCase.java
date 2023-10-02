package co.com.inventory.usecase.registersaleretail;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.generic.DomainEvent;
import co.com.inventory.model.branch.utils.Mapper;
import co.com.inventory.model.branch.values.BranchId;
import co.com.inventory.model.branch.values.ProductSaleId;
import co.com.inventory.usecase.generic.UseCaseForCommand;
import co.com.inventory.usecase.generic.commands.AddProductSaleCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class RegisterSaleRetailUseCase extends UseCaseForCommand<AddProductSaleCommand> {
    private final DomainEventRepository domainEventRepository;
    private final MySqlRepository mySqlRepository;

    public RegisterSaleRetailUseCase(DomainEventRepository domainEventRepository, MySqlRepository mySqlRepository) {
        this.domainEventRepository = domainEventRepository;
        this.mySqlRepository = mySqlRepository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AddProductSaleCommand> addProductSaleCommandMono) {
        return addProductSaleCommandMono.flatMap(addProductSaleCommand -> {
            String uuid = UUID.randomUUID().toString();
            return mySqlRepository.saveProductSales(addProductSaleCommand.getBranchId(),
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
                                .flatMap(domainEventRepository::saveEvent);

                    });
        });
    }
}
