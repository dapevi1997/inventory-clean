package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.model.branch.events.ProductUpdated;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.usecase.alpha.comands.CreateBranchCommand;
import co.com.inventory.usecase.alpha.comands.UpdateProductCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UpdateProductUseCaseTest {
    private DomainEventRepository domainEventRepository;
    private EventBus eventBus;
    private UpdateProductUseCase updateProductUseCase;

    @BeforeEach
    void setUp() {
        domainEventRepository = mock(DomainEventRepository.class);
        eventBus = mock(EventBus.class);
        updateProductUseCase = new UpdateProductUseCase(domainEventRepository,eventBus);
    }

    @Test
    void apply() {
        UpdateProductCommand updateProductCommand = new UpdateProductCommand("id",5);

        ProductUpdated productUpdated = new ProductUpdated("id",5);

        when(domainEventRepository.saveEvent(any())).thenReturn(Mono.just(productUpdated));


        // Act
        Mono<DomainEvent> result = updateProductUseCase.apply(Mono.just(updateProductCommand));

        StepVerifier.create(result)
                .expectNext(productUpdated)
                .verifyComplete();
        verify(domainEventRepository, times(1)).saveEvent(any());
        verify(eventBus, times(1)).publish(any());
    }
}