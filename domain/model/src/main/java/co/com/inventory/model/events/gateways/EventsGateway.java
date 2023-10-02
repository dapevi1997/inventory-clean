package co.com.inventory.model.events.gateways;

import reactor.core.publisher.Mono;

public interface EventsGateway {
Mono<Void> emit(Object event);

}
