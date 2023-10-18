package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.events.ProductAdded;
import co.com.inventory.model.branch.events.UserRegistered;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.usecase.alpha.comands.AddProductCommand;
import co.com.inventory.usecase.alpha.comands.AddUserCommand;
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

class RegisterUserUseCaseTest {
    private DomainEventRepository domainEventRepository;
    private EventBus eventBus;
    private RegisterUserUseCase registerUserUseCase;

    @BeforeEach
    void setUp() {
        domainEventRepository = mock(DomainEventRepository.class);
        eventBus = mock(EventBus.class);
        registerUserUseCase = new RegisterUserUseCase(domainEventRepository,eventBus);
    }

    @Test
    void apply() {
        AddUserCommand addUserCommand = new AddUserCommand("branchId","name",
                "lastname","password","email@email.com","role");

        UserRegistered userRegistered = new UserRegistered("branchId","id","name",
                "lastname","password","email@email.com","role");

        when(domainEventRepository.saveEvent(any())).thenReturn(Mono.just(userRegistered));


        // Act
        Mono<DomainEvent> result = registerUserUseCase.apply(Mono.just(addUserCommand));

        StepVerifier.create(result)
                .expectNext(userRegistered)
                .verifyComplete();
        verify(domainEventRepository, times(1)).saveEvent(any());
        verify(eventBus, times(1)).publish(any());
    }
}