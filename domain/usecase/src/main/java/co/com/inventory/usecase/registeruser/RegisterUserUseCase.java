package co.com.inventory.usecase.registeruser;


import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.usecase.generic.UseCaseForCommand;
import co.com.inventory.usecase.generic.commands.AddUserCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RegisterUserUseCase extends UseCaseForCommand<AddUserCommand> {
    private final DomainEventRepository domainEventRepository;
    private final EventBus eventBus;

    public RegisterUserUseCase(DomainEventRepository domainEventRepository, EventBus eventBus) {
        this.domainEventRepository = domainEventRepository;
        this.eventBus = eventBus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AddUserCommand> addUserCommandMono) {
        return null;
/*        return addUserCommandMono.flatMap(addUserCommand -> {
            return mySqlRepository.saveUser(addUserCommand.getBranchId(),
                    addUserCommand.getUserName(),
                    addUserCommand.getUserLastname(),
                    addUserCommand.getUserPassword(),
                    addUserCommand.getUserEmail(),
                    addUserCommand.getUserRole()
            );
        }).flatMapMany(user -> {
            return domainEventRepository.findById(user.identity().toString())
                    .collectList()
                    .flatMapMany(events -> {
                        Branch branch = Branch.from(BranchId.of(user.identity().toString()),
                                events);
                        branch.registerUser(user.identity(),
                                user.getUserName(),
                                user.getUserlastName(),
                                user.getUserPassword(),
                                user.getUserEmail(),
                                user.getUserRole()
                        );
                        return Flux.fromIterable(branch.getUncommittedChanges())
                                .flatMap(domainEventRepository::saveEvent).map(
                                        domainEvent -> {
                                            eventBus.publish(domainEvent);
                                            return domainEvent;
                                        }
                                );

                    });
        });*/
    }
}
