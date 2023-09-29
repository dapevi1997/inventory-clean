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
    //TODO: inyectar los repositorios para eventos y dominio
    private final DomainEventRepository domainEventRepository;
    private final MySqlRepository mySqlRepository;

    public CreateBranchUseCase(DomainEventRepository domainEventRepository, MySqlRepository mySqlRepository) {
        this.domainEventRepository = domainEventRepository;
        this.mySqlRepository = mySqlRepository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CreateBranchCommand> createBranchCommandMono) {
       //TODO: guardar en base de datos y el evento


        return createBranchCommandMono
                .flatMap(createBranchCommand->{

                    return mySqlRepository.saveBranch(createBranchCommand.getBranchName(),
                            createBranchCommand.getBranchLocation());
                })
                .flatMapIterable(
                branch -> {
                   // BranchName branchName = new BranchName(createBranchCommand.getBranchName());
                   // BranchLocation branchLocation = new BranchLocation(createBranchCommand.getBranchLocation());

                   // Mono<BranchId> branchIdMono = mySqlRepository.saveBranch(createBranchCommand.getBranchName(),
                          //  createBranchCommand.getBranchLocation());
                    //TODO: implementar el id
                   // Branch branch = new Branch(branchIdMono.subscribe());

                    return branch.getUncommittedChanges();
                }
        ).flatMap(
                domainEvent -> {
                    return domainEventRepository.saveEvent(domainEvent);
                }
        );


    }
}
