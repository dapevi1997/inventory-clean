package co.com.inventory.usecase.generic.gateways;

import co.com.inventory.model.branch.entities.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MySqlRepositoryQuery {
    Mono<Product> findProductbyId(String productId);
    Flux<Product> getAllProducts();
}
