package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class GetProductByIdUCTest {
    private MySqlRepositoryQuery mySqlRepositoryQuery;
    private GetProductByIdUC getProductByIdUC;

    @BeforeEach
    void setUp() {
        mySqlRepositoryQuery = mock(MySqlRepositoryQuery.class);
        getProductByIdUC = new GetProductByIdUC(mySqlRepositoryQuery);
    }

    @Test
    void execute() {
        Product product = new Product(BranchId.of("id"), ProductId.of("idProduct"),
                new ProductName("name"), new ProductDescription("description"),new ProductPrice("500"),
                new ProductInventoryStock("5"),new ProductCategory("category"));

        when(mySqlRepositoryQuery.findProductbyId(anyString()))
                .thenReturn(Mono.just(product));

        // Act
        Mono<Product> result = getProductByIdUC.execute("id");

        StepVerifier.create(result)
                .expectNext(product)
                .verifyComplete();
        verify(mySqlRepositoryQuery, times(1)).findProductbyId("id");
    }
}