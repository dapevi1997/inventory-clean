package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class GetUserByEmailUCTest {
    private MySqlRepositoryQuery mySqlRepositoryQuery;
    private GetUserByEmailUC getUserByEmailUC;

    @BeforeEach
    void setUp() {
        mySqlRepositoryQuery = mock(MySqlRepositoryQuery.class);
        getUserByEmailUC = new GetUserByEmailUC(mySqlRepositoryQuery);
    }

    @Test
    void execute() {
        User user = new User(BranchId.of("idBranch"), UserId.of("id"),
                new UserName("name"), new UserlastName("lastname"),new UserPassword("password"),
                new UserEmail("user@user.com"),new UserRole("role"));

        when(mySqlRepositoryQuery.findUserByEmail(anyString()))
                .thenReturn(Mono.just(user));

        // Act
        Mono<User> result = getUserByEmailUC.execute("user@user.com");

        StepVerifier.create(result)
                .expectNext(user)
                .verifyComplete();
        verify(mySqlRepositoryQuery, times(1)).findUserByEmail("user@user.com");
    }
}