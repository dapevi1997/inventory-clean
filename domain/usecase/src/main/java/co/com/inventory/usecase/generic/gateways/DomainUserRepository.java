package co.com.inventory.usecase.generic.gateways;

import co.com.inventory.model.branch.entities.User;
import co.com.inventory.usecase.utils.AuthRequest;
import co.com.inventory.usecase.utils.AuthResponse;
import reactor.core.publisher.Mono;

public interface DomainUserRepository {
    public Mono<User> saveAUser(User user);

    public Mono<User> saveASuper(User user);
    public Mono<AuthResponse> authenticate(AuthRequest authRequest);
}
