package co.com.inventory.mysql.config;


import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.mysql.config.repositories.ProductRepository;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MySQLAdapterForQuery implements MySqlRepositoryQuery {
    private final ProductRepository productRepository;

    public MySQLAdapterForQuery(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
}
