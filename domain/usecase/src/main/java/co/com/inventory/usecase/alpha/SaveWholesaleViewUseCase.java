package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class SaveWholesaleViewUseCase {
    private final MySqlRepository mySqlRepository;
    public SaveWholesaleViewUseCase(MySqlRepository mySqlRepository) {
        this.mySqlRepository = mySqlRepository;
    }
    public Flux<ProductSale> execute(String branchId, List<ProductSale> productSaleList, Float discount, String type, String user){
        return mySqlRepository.saveSale( branchId, productSaleList, discount, type,user
               );
    }
}
