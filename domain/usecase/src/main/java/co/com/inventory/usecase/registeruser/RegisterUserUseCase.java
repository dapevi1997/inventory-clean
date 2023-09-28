package co.com.inventory.usecase.registeruser;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.generic.DomainEvent;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.generic.UseCaseForCommand;
import co.com.inventory.usecase.generic.commands.AddUserCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RegisterUserUseCase extends UseCaseForCommand<AddUserCommand> {
    private final DomainEventRepository domainEventRepository;

    public RegisterUserUseCase(DomainEventRepository domainEventRepository) {
        this.domainEventRepository = domainEventRepository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AddUserCommand> addUserCommandMono) {
        return addUserCommandMono.flatMapMany(
                addUserCommand ->
                        domainEventRepository.findById(addUserCommand.getBranchId())
                                .collectList()
                                .flatMapIterable( events ->
                                        {
                                            Branch branch = Branch.from(BranchId.of(addUserCommand.getBranchId()),events);

                                            branch.registerUser(UserId.of(addUserCommand.getUserId()),
                                                    new UserName(addUserCommand.getUserName()),
                                                    new UserPassword(addUserCommand.getUserPassword()),
                                                    new UserEmail(addUserCommand.getUserEmail()),
                                                    new UserRole(addUserCommand.getUserRole())
                                            );

                                            return branch.getUncommittedChanges();
                                        }
                                ).flatMap(
                                        domainEvent -> {
                                            return domainEventRepository.saveEvent(domainEvent);
                                        }
                                ));
    }
}
