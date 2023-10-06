package co.com.inventory.mysql.config;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.mysql.config.repositories.BranchRepository;
import co.com.inventory.mysql.config.repositories.ProductRepository;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MySQLAdapterForQuery implements MySqlRepositoryQuery {
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    public MySQLAdapterForQuery(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public Mono<Product> findProductbyId(String productId) {
        return productRepository.findById(productId)
                .map(productMySQL -> {
                   // return productMySQL.getProductPrice();
                    return new Product(ProductId.of(productMySQL.getProductId().toString()),
                            new ProductName(productMySQL.getProductName()),
                            new ProductDescription(productMySQL.getProductDescription()),
                            new ProductPrice(productMySQL.getProductPrice().toString()),
                            new ProductInventoryStock(productMySQL.getProductInventoryStock().toString()),
                            new ProductCategory(productMySQL.getProductCategory()));
                });
    }

    @Override
    public Flux<Product> getAllProducts() {
        return productRepository.findAll().map(
                productMySQL -> {
                    Product product = new Product(ProductId.of(productMySQL.getProductId().toString()),
                            new ProductName(productMySQL.getProductName()),
                            new ProductDescription(productMySQL.getProductDescription()),
                            new ProductPrice(productMySQL.getProductPrice().toString()),
                            new ProductInventoryStock(productMySQL.getProductInventoryStock().toString()),
                            new ProductCategory(productMySQL.getProductCategory()));
                    return product;
                }
        );
    }

    @Override
    public Flux<Branch> getAllBranchs() {
        return branchRepository.findAll().map(
                branchMySQL -> {
                    Branch branch = new Branch(BranchId.of(branchMySQL.getBranch_id()),
                            new BranchName(branchMySQL.getBranchName()),
                            new BranchLocation(branchMySQL.getBranch_country(), branchMySQL.getBranchCity()));

                    return branch;
                }
        );
    }
}
