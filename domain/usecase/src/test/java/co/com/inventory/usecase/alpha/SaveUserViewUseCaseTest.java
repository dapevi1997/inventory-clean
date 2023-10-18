package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class SaveUserViewUseCaseTest {
    private MySqlRepository mySqlRepository;
    private SaveUserViewUseCase saveUserViewUseCase;

    @BeforeEach
    void setUp() {
        mySqlRepository = mock(MySqlRepository.class);
        saveUserViewUseCase = new SaveUserViewUseCase(mySqlRepository);
    }

    @Test
    void execute() {
        User user = new User(BranchId.of("idBranch"), UserId.of("id"),
                new UserName("name"), new UserlastName("lastname"),new UserPassword("password"),
                new UserEmail("user@user.com"),new UserRole("role"));

        when(mySqlRepository.saveUser(anyString(),anyString(),anyString(),anyString(),anyString(),anyString()))
                .thenReturn(Mono.just(user));

        // Act
        Mono<User> result = saveUserViewUseCase.execute("idBranch","name",
                "lastname","password","user@user.com","role");

        StepVerifier.create(result)
                .expectNext(user)
                .verifyComplete();
        verify(mySqlRepository, times(1)).saveUser(anyString(),anyString(),anyString(),anyString(),anyString(),anyString());
    }
}