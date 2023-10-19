package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class GetBranchsUCTest {
    private MySqlRepositoryQuery mySqlRepositoryQuery;
    private GetBranchsUC getBranchsUC;

    @BeforeEach
    void setUp() {
        mySqlRepositoryQuery = mock(MySqlRepositoryQuery.class);
        getBranchsUC = new GetBranchsUC(mySqlRepositoryQuery);
    }

    @Test
    void execute() {
        Branch branch = new Branch(BranchId.of("id"),new BranchName("name"),
                new BranchLocation("country","city"));

        when(mySqlRepositoryQuery.getAllBranchs())
                .thenReturn(Flux.just(branch));

        // Act
        Flux<Branch> result = getBranchsUC.execute();

        StepVerifier.create(result)
                .expectNext(branch)
                .verifyComplete();
        verify(mySqlRepositoryQuery, times(1)).getAllBranchs();
    }
}