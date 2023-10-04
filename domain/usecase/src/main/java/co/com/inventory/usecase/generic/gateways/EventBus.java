package co.com.inventory.usecase.generic.gateways;


import co.com.inventory.model.branch.utils.DomainEvent;

public interface EventBus {
    void publish(DomainEvent event);

    void publishError(Throwable errorEvent);
}
