package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.values.BranchId;
import co.com.inventory.model.branch.values.BranchLocation;
import co.com.inventory.model.branch.values.BranchName;
import co.com.inventory.usecase.beta.SaveBranchViewUC;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class SaveBranchViewUCTest {
    private MySqlRepositoryQuery mySqlRepositoryQuery;
    private SaveBranchViewUC saveBranchViewUC;

    @BeforeEach
    void setUp() {
        mySqlRepositoryQuery = mock(MySqlRepositoryQuery.class);
        saveBranchViewUC = new SaveBranchViewUC(mySqlRepositoryQuery);
    }

    @Test
    void execute() {
        Branch branch = new Branch(BranchId.of("id"),new BranchName("name"),
                new BranchLocation("country","city"));

        when(mySqlRepositoryQuery.saveBranch(anyString(),anyString(),anyString(),anyString()))
                .thenReturn(Mono.just(branch));

        // Act
        Mono<Branch> result = saveBranchViewUC.execute("id","name","country","city");

        StepVerifier.create(result)
                .expectNext(branch)
                .verifyComplete();
        verify(mySqlRepositoryQuery, times(1)).saveBranch(anyString(),anyString(),anyString(),anyString());

    }
}