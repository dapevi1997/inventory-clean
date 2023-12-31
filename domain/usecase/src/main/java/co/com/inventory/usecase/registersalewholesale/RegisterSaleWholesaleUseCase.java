package co.com.inventory.usecase.registersalewholesale;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.generic.DomainEvent;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.generic.UseCaseForCommand;
import co.com.inventory.usecase.generic.commands.AddProductSaleCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.utils.MapperUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegisterSaleWholesaleUseCase extends UseCaseForCommand<AddProductSaleCommand> {
    private final DomainEventRepository domainEventRepository;

    public RegisterSaleWholesaleUseCase(DomainEventRepository domainEventRepository) {
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
                                                            productSale.setProductSalePrice(new ProductSalePrice(product.getProductPrice().getProductPrice() * 0.7F));
                                                            return productSale;
                                                        });

                                            }).collect(Collectors.toList());

                                            branch.registerSaleWholesale(ProductSaleId.of(addProductSaleCommand.getProductSaleId()), collect

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
