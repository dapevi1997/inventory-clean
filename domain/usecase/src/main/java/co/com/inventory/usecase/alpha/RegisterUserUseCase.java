package co.com.inventory.usecase.alpha;



import co.com.inventory.model.branch.events.UserRegistered;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.alpha.comands.AddUserCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class RegisterUserUseCase{
    private final DomainEventRepository domainEventRepository;
    private final EventBus eventBus;

    public RegisterUserUseCase(DomainEventRepository domainEventRepository, EventBus eventBus) {
        this.domainEventRepository = domainEventRepository;
        this.eventBus = eventBus;
    }


    public Mono<DomainEvent> apply(Mono<AddUserCommand> addUserCommandMono) {
        return addUserCommandMono.flatMap(addUserCommand -> {
            BranchId.of(addUserCommand.getBranchId());
            new UserName(addUserCommand.getUserName());
            new UserlastName(addUserCommand.getUserLastname());
            new UserPassword(addUserCommand.getUserPassword());
            new UserEmail(addUserCommand.getUserEmail());
            new UserRole(addUserCommand.getUserRole());

            UserRegistered userRegistered = new UserRegistered(
                    addUserCommand.getBranchId(),
                    UUID.randomUUID().toString(),
                    addUserCommand.getUserName(),
                    addUserCommand.getUserLastname(),
                    addUserCommand.getUserPassword(),
                    addUserCommand.getUserEmail(),
                    addUserCommand.getUserRole()
            );




            return domainEventRepository.saveEvent(userRegistered)
                    .map(domainEvent -> {
                        eventBus.publish(domainEvent);
                        return domainEvent;
                    });
        });
    }
}
