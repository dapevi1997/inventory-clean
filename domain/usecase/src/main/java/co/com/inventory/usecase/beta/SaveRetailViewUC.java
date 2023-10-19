package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import reactor.core.publisher.Flux;

import java.util.List;

public class SaveRetailViewUC {
    private final MySqlRepositoryQuery mySqlRepositoryQuery;
    public SaveRetailViewUC(MySqlRepositoryQuery mySqlRepositoryQuery) {

        this.mySqlRepositoryQuery = mySqlRepositoryQuery;
    }
    public Flux<ProductSale> execute(String branchId, List<ProductSale> productSaleList, Float discount, String type, String user){
        return mySqlRepositoryQuery.saveSale( branchId, productSaleList, discount, type,user
               );
    }
}
