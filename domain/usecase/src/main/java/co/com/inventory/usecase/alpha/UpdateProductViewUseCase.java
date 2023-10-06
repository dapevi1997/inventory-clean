package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import reactor.core.publisher.Mono;

public class UpdateProductViewUseCase {
    private final MySqlRepository mySqlRepository;
    public UpdateProductViewUseCase(MySqlRepository mySqlRepository) {
        this.mySqlRepository = mySqlRepository;
    }
    public Mono<Product> execute(String idProduct, Integer productInventoryStock
                          ){
        return mySqlRepository.updateProductStock( idProduct, productInventoryStock
            );
    }
}
