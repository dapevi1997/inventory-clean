package co.com.inventory.usecase.auth;

import co.com.inventory.usecase.alpha.comands.LoginCommand;
import co.com.inventory.usecase.generic.gateways.DomainUserRepository;
import co.com.inventory.usecase.utils.AuthRequest;
import co.com.inventory.usecase.utils.AuthResponse;
import reactor.core.publisher.Mono;

public class LoginUsC {
    private final DomainUserRepository domainUserRepository;

    public LoginUsC(DomainUserRepository domainUserRepository) {
        this.domainUserRepository = domainUserRepository;
    }

    public Mono<AuthResponse> apply(Mono<LoginCommand> command) {
        return command.flatMap(command1 -> domainUserRepository.authenticate(
                new AuthRequest(command1.getEmail(), command1.getPassword())
        ));
    }
}
