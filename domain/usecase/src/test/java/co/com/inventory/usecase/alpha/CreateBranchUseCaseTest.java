package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.model.branch.events.ProductAdded;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.usecase.alpha.comands.AddProductCommand;
import co.com.inventory.usecase.alpha.comands.CreateBranchCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CreateBranchUseCaseTest {

    private DomainEventRepository domainEventRepository;
    private EventBus eventBus;
    private CreateBranchUseCase createBranchUseCase;
    @BeforeEach
    void setUp() {
        domainEventRepository = mock(DomainEventRepository.class);
        eventBus = mock(EventBus.class);
        createBranchUseCase = new CreateBranchUseCase(domainEventRepository,eventBus);
    }

    @Test
    public void testAddProduct(){
        CreateBranchCommand createBranchCommand = new CreateBranchCommand("name","country",
                "city");

        BranchCreated branchCreated = new BranchCreated("name","country","city");

        when(domainEventRepository.saveEvent(any())).thenReturn(Mono.just(branchCreated));


        // Act
        Mono<DomainEvent> result = createBranchUseCase.apply(Mono.just(createBranchCommand));

        StepVerifier.create(result)
                .expectNext(branchCreated)
                .verifyComplete();
        verify(domainEventRepository, times(1)).saveEvent(any());
        verify(eventBus, times(1)).publish(any());

    }
}