package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.beta.SaveUserViewUC;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class SaveUserViewUCTest {
    private MySqlRepositoryQuery mySqlRepositoryQuery;
    private SaveUserViewUC saveUserViewUC;

    @BeforeEach
    void setUp() {
        mySqlRepositoryQuery = mock(MySqlRepositoryQuery.class);
        saveUserViewUC = new SaveUserViewUC(mySqlRepositoryQuery);
    }

    @Test
    void execute() {
        User user = new User(BranchId.of("idBranch"), UserId.of("id"),
                new UserName("name"), new UserlastName("lastname"),new UserPassword("password"),
                new UserEmail("user@user.com"),new UserRole("role"));

        when(mySqlRepositoryQuery.saveUser(anyString(),anyString(),anyString(),anyString(),anyString(),anyString()))
                .thenReturn(Mono.just(user));

        // Act
        Mono<User> result = saveUserViewUC.execute("idBranch","name",
                "lastname","password","user@user.com","role");

        StepVerifier.create(result)
                .expectNext(user)
                .verifyComplete();
        verify(mySqlRepositoryQuery, times(1)).saveUser(anyString(),anyString(),anyString(),anyString(),anyString(),anyString());
    }
}