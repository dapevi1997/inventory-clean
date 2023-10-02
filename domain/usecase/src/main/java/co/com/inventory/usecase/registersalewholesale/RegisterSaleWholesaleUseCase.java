package co.com.inventory.usecase.registersalewholesale;


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

public class RegisterSaleWholesaleUseCase extends UseCaseForCommand<AddProductSaleCommand> {
    private final DomainEventRepository domainEventRepository;
    private final MySqlRepository mySqlRepository;

    public RegisterSaleWholesaleUseCase(DomainEventRepository domainEventRepository, MySqlRepository mySqlRepository) {
        this.domainEventRepository = domainEventRepository;
        this.mySqlRepository = mySqlRepository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AddProductSaleCommand> addProductSaleCommandMono) {

        return addProductSaleCommandMono.flatMap(addProductSaleCommand -> {
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
                                .flatMap(domainEventRepository::saveEvent);

                    });
        });

/*        return addProductSaleCommandMono.flatMapMany(
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
                                                            Float result = product.getProductPrice().getProductPrice() * 0.7F;
                                                            productSale.setProductSalePrice(new ProductSalePrice(result.toString()));
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
                                ));*/
    }
}
