package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.events.ProductAdded;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.usecase.alpha.comands.AddProductCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AddProductUseCaseTest {

    private DomainEventRepository domainEventRepository;
    private EventBus eventBus;
    private AddProductUseCase addProductUseCase;

    @BeforeEach
    void setUp() {
        domainEventRepository = mock(DomainEventRepository.class);
        eventBus = mock(EventBus.class);
        addProductUseCase = new AddProductUseCase(domainEventRepository,eventBus);
    }

    @Test
    public void testAddProduct(){
        AddProductCommand addProductCommand = new AddProductCommand("branchId","productName",
                "description","500","200","category");

        ProductAdded productAdded = new ProductAdded("branchId","productId","productName",
                "description","500","200","category");

        when(domainEventRepository.saveEvent(any())).thenReturn(Mono.just(productAdded));


        // Act
        Mono<DomainEvent> result = addProductUseCase.apply(Mono.just(addProductCommand));

        StepVerifier.create(result)
                .expectNext(productAdded)
                        .verifyComplete();
        verify(domainEventRepository, times(1)).saveEvent(any());
        verify(eventBus, times(1)).publish(any());

    }
}