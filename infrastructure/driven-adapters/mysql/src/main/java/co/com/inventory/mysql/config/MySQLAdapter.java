package co.com.inventory.mysql.config;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.mysql.config.models.BranchMySQL;
import co.com.inventory.mysql.config.models.ProductMySQL;
import co.com.inventory.mysql.config.repositories.BranchRepository;
import co.com.inventory.mysql.config.repositories.ProductRepository;
import co.com.inventory.mysql.config.utilities.MapperUtils;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Repository
public class MySQLAdapter implements MySqlRepository {

    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final MapperUtils mapperUtils;

    public MySQLAdapter(BranchRepository branchRepository, ProductRepository productRepository, MapperUtils mapperUtils) {
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<Branch> saveBranch(String branchNameP, String branchCountry, String branchCity) {
        BranchMySQL branchMySQLAux = new BranchMySQL();
        BranchName branchName = new BranchName(branchNameP);
        BranchLocation branchLocation = new BranchLocation(branchCountry,branchCity);

        branchMySQLAux.setBranchName(branchNameP);
        branchMySQLAux.setBranch_country(branchCountry);
        branchMySQLAux.setBranchCity(branchCity);

        return branchRepository.save(branchMySQLAux).map(branchMySQL -> {
            return new Branch(BranchId.of(branchMySQL.getId().toString()),
                    branchName,branchLocation) ;
        });

    }

    @Override
    public Mono<Product> saveProduct(String branchId, String productName, String description, String productPrice, String productInventoryStock, String productCategory) {
        try {
            Long.parseLong(branchId);
        }catch (NumberFormatException e){
            throw new NumberFormatException("El campo branchId debe ser un entero y no puede ser null");
        }

        ProductMySQL productMySQL = new ProductMySQL();
        productMySQL.setBranchId(Long.parseLong(branchId));
        productMySQL.setProductName(productName);
        productMySQL.setProductDescription(description);
        productMySQL.setProductPrice(Float.parseFloat(productPrice));
        productMySQL.setProductInventoryStock(Integer.parseInt(productInventoryStock));
        productMySQL.setProductCategory(productCategory);



        return productRepository.save(productMySQL).map(productMySQL1 -> {
            return new Product(ProductId.of(productMySQL1.getId().toString()),
                    new ProductName(productName),
                    new ProductDescription(description),
                    new ProductPrice(productPrice),
                    new ProductInventoryStock(productInventoryStock),
                    new ProductCategory(productCategory));
        });
    }
}
