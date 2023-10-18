package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.model.branch.values.BranchId;
import co.com.inventory.model.branch.values.BranchLocation;
import co.com.inventory.model.branch.values.BranchName;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class SaveBranchViewUseCaseTest {
    private MySqlRepository mySqlRepository;
    private SaveBranchViewUseCase saveBranchViewUseCase;

    @BeforeEach
    void setUp() {
        mySqlRepository = mock(MySqlRepository.class);
        saveBranchViewUseCase = new SaveBranchViewUseCase(mySqlRepository);
    }

    @Test
    void execute() {
        Branch branch = new Branch(BranchId.of("id"),new BranchName("name"),
                new BranchLocation("country","city"));

        when(mySqlRepository.saveBranch(anyString(),anyString(),anyString(),anyString()))
                .thenReturn(Mono.just(branch));

        // Act
        Mono<Branch> result = saveBranchViewUseCase.execute("id","name","country","city");

        StepVerifier.create(result)
                .expectNext(branch)
                .verifyComplete();
        verify(mySqlRepository, times(1)).saveBranch(anyString(),anyString(),anyString(),anyString());

    }
}