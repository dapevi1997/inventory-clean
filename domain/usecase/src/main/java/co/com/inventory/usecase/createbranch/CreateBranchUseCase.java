package co.com.inventory.usecase.createbranch;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.generic.DomainEvent;
import co.com.inventory.model.branch.values.BranchId;
import co.com.inventory.model.branch.values.BranchLocation;
import co.com.inventory.model.branch.values.BranchName;
import co.com.inventory.usecase.generic.UseCaseForCommand;
import co.com.inventory.usecase.generic.commands.CreateBranchCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class CreateBranchUseCase extends UseCaseForCommand<CreateBranchCommand> {
    //TODO: inyectar los repositorios para eventos y dominio
    private final DomainEventRepository domainEventRepository;

    public CreateBranchUseCase(DomainEventRepository domainEventRepository) {
        this.domainEventRepository = domainEventRepository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CreateBranchCommand> createBranchCommandMono) {
       //TODO: guardar en base de datos y el evento


        return createBranchCommandMono.flatMapIterable(
                createBranchCommand -> {
                    BranchName branchName = new BranchName(createBranchCommand.getBranchName());
                    BranchLocation branchLocation = new BranchLocation(createBranchCommand.getBranchLocation());
                    Branch branch = new Branch(BranchId.of(createBranchCommand.getBranchId()),branchName,branchLocation);

                    return branch.getUncommittedChanges();
                }
        ).flatMap(
                domainEvent -> {
                    return domainEventRepository.saveEvent(domainEvent);
                }
        );


    }
}
