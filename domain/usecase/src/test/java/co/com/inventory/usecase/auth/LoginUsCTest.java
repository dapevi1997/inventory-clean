package co.com.inventory.usecase.auth;

import co.com.inventory.usecase.alpha.comands.LoginCommand;
import co.com.inventory.usecase.generic.gateways.DomainUserRepository;
import co.com.inventory.usecase.utils.AuthRequest;
import co.com.inventory.usecase.utils.AuthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginUsCTest {
    @Mock
    private DomainUserRepository domainUserRepository;

    private LoginUsC loginUsC;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        loginUsC = new LoginUsC(domainUserRepository);
    }
    @Test
    void apply(){
        LoginCommand loginCommand = new LoginCommand("test@email.com","testpassword");
        AuthResponse authResponse = new AuthResponse("accesToken","ROLE_ROLE");
        when(domainUserRepository.authenticate(Mockito.any(AuthRequest.class))).thenReturn(Mono.just(authResponse));

        Mono<AuthResponse> result = loginUsC.apply(Mono.just(loginCommand));

        StepVerifier.create(result)
                .expectNext(authResponse)
                .verifyComplete();
    }
}