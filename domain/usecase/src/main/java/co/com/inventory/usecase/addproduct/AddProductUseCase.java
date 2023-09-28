package co.com.inventory.usecase.addproduct;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.generic.DomainEvent;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.generic.UseCaseForCommand;
import co.com.inventory.usecase.generic.commands.AddProductCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AddProductUseCase extends UseCaseForCommand<AddProductCommand> {
    //TODO: inyectar el bus event
    private final DomainEventRepository domainEventRepository;

    public AddProductUseCase(DomainEventRepository domainEventRepository) {
        this.domainEventRepository = domainEventRepository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AddProductCommand> addProductCommandMono) {
        return addProductCommandMono.flatMapMany(
                addProductCommand ->
                    domainEventRepository.findById(addProductCommand.getBranchId())
                            .collectList()
                            .flatMapIterable( events ->
                                    {
                                        Branch branch = Branch.from(BranchId.of(addProductCommand.getBranchId()),events);

                                        branch.addProduct(ProductId.of(addProductCommand.getProductId()),
                                                new ProductName(addProductCommand.getProductName()),
                                                new ProductDescription(addProductCommand.getProductDescription()),
                                                new ProductPrice(addProductCommand.getProductPrice()),
                                                new ProductInventoryStock(addProductCommand.getProductInventoryStock()),
                                                new ProductCategory(addProductCommand.getProductCategory())
                                        );

                                        return branch.getUncommittedChanges();
                                    }
                            ).flatMap(
                                    domainEvent -> {
                                        return domainEventRepository.saveEvent(domainEvent);
                                    }
                            ));

    }
}
