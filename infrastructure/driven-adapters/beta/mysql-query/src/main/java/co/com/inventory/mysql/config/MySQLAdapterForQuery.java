package co.com.inventory.mysql.config;


import co.com.inventory.mysql.config.repositories.ProductRepository;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MySQLAdapterForQuery implements MySqlRepositoryQuery {
    private final ProductRepository productRepository;

    public MySQLAdapterForQuery(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Mono<Float> findProductbyId(String productId) {
        return productRepository.findById(productId)
                .map(productMySQL -> {
                    return productMySQL.getProductPrice();
/*                    return new Product(ProductId.of(productMySQL.getProductId().toString()),
                            new ProductName(productMySQL.getProductName()),
                            new ProductDescription(productMySQL.getProductDescription()),
                            new ProductPrice(productMySQL.getProductPrice().toString()),
                            new ProductInventoryStock(productMySQL.getProductInventoryStock().toString()),
                            new ProductCategory(productMySQL.getProductCategory()));*/
                });
    }

}
