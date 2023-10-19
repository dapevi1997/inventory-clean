package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.beta.SaveProductViewUC;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class SaveProductViewUCTest {
    private MySqlRepositoryQuery mySqlRepositoryQuery;
    private SaveProductViewUC saveProductViewUC;

    @BeforeEach
    void setUp() {
        mySqlRepositoryQuery = mock(MySqlRepositoryQuery.class);
        saveProductViewUC = new SaveProductViewUC(mySqlRepositoryQuery);
    }

    @Test
    void execute() {
        Product product = new Product(BranchId.of("id"), ProductId.of("idProduct"),
                new ProductName("name"), new ProductDescription("description"),new ProductPrice("500"),
                new ProductInventoryStock("5"),new ProductCategory("category"));

        when(mySqlRepositoryQuery.saveProduct(anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString()))
                .thenReturn(Mono.just(product));

        // Act
        Mono<Product> result = saveProductViewUC.execute("id","idProduct",
                "name","description","500","5","category");

        StepVerifier.create(result)
                .expectNext(product)
                .verifyComplete();
        verify(mySqlRepositoryQuery, times(1)).saveProduct(anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString());
    }
}