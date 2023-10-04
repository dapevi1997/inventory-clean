package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import reactor.core.publisher.Mono;

public class SaveProductViewUseCase {
    private final MySqlRepository mySqlRepository;
    public SaveProductViewUseCase(MySqlRepository mySqlRepository) {
        this.mySqlRepository = mySqlRepository;
    }
    public Mono<Product> execute(String branchId,
                                 String productId,
                                 String productName,
                                 String description,
                                 String productPrice,
                                 String productInventoryStock,
                                 String productCategory){
        return mySqlRepository.saveProduct( branchId, productId,
                productName,
                 description,
                 productPrice,
                 productInventoryStock,
                 productCategory);
    }
}
