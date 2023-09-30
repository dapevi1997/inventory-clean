package co.com.inventory.usecase.addproduct;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.generic.DomainEvent;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.generic.UseCaseForCommand;
import co.com.inventory.usecase.generic.commands.AddProductCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class AddProductUseCase extends UseCaseForCommand<AddProductCommand> {
    private final DomainEventRepository domainEventRepository;
    private final MySqlRepository mySqlRepository;

    public AddProductUseCase(DomainEventRepository domainEventRepository, MySqlRepository mySqlRepository) {
        this.domainEventRepository = domainEventRepository;
        this.mySqlRepository = mySqlRepository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AddProductCommand> addProductCommandMono) {
        return addProductCommandMono.flatMap(addProductCommand -> {
            return mySqlRepository.saveProduct(addProductCommand.getBranchId(),
                    addProductCommand.getProductName(), addProductCommand.getProductDescription(),
                    addProductCommand.getProductPrice(),
                    addProductCommand.getProductInventoryStock(),
                    addProductCommand.getProductCategory()
                    );
        }).flatMapMany(product -> {
            return domainEventRepository.findById(product.identity().toString())
                    .collectList()
                    .flatMapMany(events -> {
                        Branch branch = Branch.from(BranchId.of(product.identity().toString()),
                                events);
                        branch.addProduct(product.identity(),
                                product.getProductName(),
                                product.getProductDescription(),
                                product.getProductPrice(),
                                product.getProductInventoryStock(),
                                product.getProductCategory()
                        );
                        return Flux.fromIterable(branch.getUncommittedChanges())
                                .flatMap(domainEventRepository::saveEvent);

                    });
        });



    }
}
