package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public class SaveRetailViewUseCase {
    private final MySqlRepository mySqlRepository;
    public SaveRetailViewUseCase(MySqlRepository mySqlRepository) {
        this.mySqlRepository = mySqlRepository;
    }
    public Flux<ProductSale> execute(String branchId, List<ProductSale> productSaleList, Float discount){
        return mySqlRepository.saveSale( branchId, productSaleList, discount
               );
    }
}
