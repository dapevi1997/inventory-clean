package co.com.inventory.usecase.registersaleretail;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.generic.DomainEvent;
import co.com.inventory.model.branch.values.BranchId;
import co.com.inventory.model.branch.values.ProductSaleId;
import co.com.inventory.model.branch.values.ProductSalePrice;
import co.com.inventory.usecase.generic.UseCaseForCommand;
import co.com.inventory.usecase.generic.commands.AddProductSaleCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.utils.MapperUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

public class RegisterSaleRetailUseCase extends UseCaseForCommand<AddProductSaleCommand> {
    private final DomainEventRepository domainEventRepository;

    public RegisterSaleRetailUseCase(DomainEventRepository domainEventRepository) {
        this.domainEventRepository = domainEventRepository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AddProductSaleCommand> addProductSaleCommandMono) {
        return addProductSaleCommandMono.flatMapMany(
                addProductSaleCommand ->
                        domainEventRepository.findById(addProductSaleCommand.getBranchId())
                                .collectList()
                                .flatMapIterable( events ->
                                        {
                                            Branch branch = Branch.from(BranchId.of(addProductSaleCommand.getBranchId()),events);


                                            List<ProductSale> productSales = MapperUtils.mapperListProductSaleUtilToListProductSale().apply(addProductSaleCommand.getProductSalesUtil());

                                            List<ProductSale> collect = productSales.stream().flatMap(productSale -> {
                                                return branch.getProducts()
                                                        .stream()
                                                        .filter(product ->
                                                                product.identity().value().equals(productSale.identity().value()) )
                                                        .map(product -> {
                                                            Float result = product.getProductPrice().getProductPrice()*0.8F;
                                                            productSale.setProductSalePrice(new ProductSalePrice( result.toString()));
                                                            return productSale;
                                                        });

                                            }).collect(Collectors.toList());

                                            branch.registerSaleRetail(ProductSaleId.of(addProductSaleCommand.getProductSaleId()), collect

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
