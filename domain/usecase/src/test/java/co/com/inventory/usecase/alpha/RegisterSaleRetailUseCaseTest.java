package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.model.branch.events.ProductSoldRetail;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.model.branch.utils.ProductSaleUtil;
import co.com.inventory.usecase.alpha.comands.AddProductSaleCommand;
import co.com.inventory.usecase.alpha.comands.CreateBranchCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class RegisterSaleRetailUseCaseTest {

    private DomainEventRepository domainEventRepository;
    private EventBus eventBus;
    private RegisterSaleRetailUseCase registerSaleRetailUseCase;
    @BeforeEach
    void setUp() {
        domainEventRepository = mock(DomainEventRepository.class);
        eventBus = mock(EventBus.class);
        registerSaleRetailUseCase = new RegisterSaleRetailUseCase(domainEventRepository,eventBus);
    }

    @Test
    void apply() {

        List<ProductSaleUtil> productSaleUtils = new ArrayList<>();
        ProductSaleUtil productSaleUtil = new ProductSaleUtil("productId","1");
        productSaleUtils.add(productSaleUtil);

        AddProductSaleCommand addProductSaleCommand = new AddProductSaleCommand("branchId","productSaleId",
                productSaleUtils);

        ProductSoldRetail productSoldRetail = new ProductSoldRetail("branchId","productSaleId",
                "[{\"productSaleId\":\"productId\",\"productSaleStock\":\"1\"}]");

        when(domainEventRepository.saveEvent(any())).thenReturn(Mono.just(productSoldRetail));


        // Act
        Mono<DomainEvent> result = registerSaleRetailUseCase.apply(Mono.just(addProductSaleCommand));

        StepVerifier.create(result)
                .expectNext(productSoldRetail)
                .verifyComplete();
        verify(domainEventRepository, times(1)).saveEvent(any());
        verify(eventBus, times(1)).publish(any());
    }
}