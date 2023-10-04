package co.com.inventory.usecase.alpha;


import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.model.branch.utils.StoredEventModel;
import co.com.inventory.usecase.generic.commands.CreateBranchCommand;
import co.com.inventory.usecase.generic.gateways.DomainEventRepository;
import co.com.inventory.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class CreateBranchUseCase{
    private final DomainEventRepository domainEventRepository;
    private final EventBus eventBus;


    public CreateBranchUseCase(DomainEventRepository domainEventRepository, EventBus eventBus) {
        this.domainEventRepository = domainEventRepository;
        this.eventBus = eventBus;
    }

    public Mono<DomainEvent> apply(Mono<CreateBranchCommand> createBranchCommandMono) {

        return createBranchCommandMono.flatMap(createBranchCommand -> {
            BranchCreated branchCreated = new BranchCreated(
                    createBranchCommand.getBranchName(),
                    createBranchCommand.getBranchCountry(),
                    createBranchCommand.getBranchCity()
            );

            return domainEventRepository.saveEvent(branchCreated)
                    .map(domainEvent -> {
                        eventBus.publish(domainEvent);
                        return domainEvent;
                    });
        });

    }
}
