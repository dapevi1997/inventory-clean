package co.com.inventory.usecase.generic.gateways;

import co.com.inventory.model.branch.entities.Product;
import reactor.core.publisher.Mono;

public interface MySqlRepositoryQuery {
    Mono<Float> findProductbyId(String productId);
}
