package co.com.inventory.usecase.createbranch;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.generic.DomainEvent;
import co.com.inventory.model.branch.values.BranchId;
import co.com.inventory.model.branch.values.BranchLocation;
import co.com.inventory.model.branch.values.BranchName;
import co.com.inventory.usecase.generic.UseCaseForCommand;
import co.com.inventory.usecase.generic.commands.CreateBranchCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class CreateBranchUseCase extends UseCaseForCommand<CreateBranchCommand> {
    private final DomainEventRepository domainEventRepository;
    private final MySqlRepository mySqlRepository;

    public CreateBranchUseCase(DomainEventRepository domainEventRepository, MySqlRepository mySqlRepository) {
        this.domainEventRepository = domainEventRepository;
        this.mySqlRepository = mySqlRepository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CreateBranchCommand> createBranchCommandMono) {
        return createBranchCommandMono
                .flatMap(createBranchCommand->{
                    return mySqlRepository.saveBranch(createBranchCommand.getBranchName(),
                            createBranchCommand.getBranchCountry(), createBranchCommand.getBranchCity());
                })
                .flatMapIterable(
                branch -> {
                    return branch.getUncommittedChanges();
                }
        ).flatMap(
                domainEvent -> {
                    return domainEventRepository.saveEvent(domainEvent);
                }
        );
    }
}
