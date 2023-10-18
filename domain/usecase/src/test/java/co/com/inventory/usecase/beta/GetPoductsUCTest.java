package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class GetPoductsUCTest {
    private MySqlRepositoryQuery mySqlRepositoryQuery;
    private GetPoductsUC getPoductsUC;

    @BeforeEach
    void setUp() {
        mySqlRepositoryQuery = mock(MySqlRepositoryQuery.class);
        getPoductsUC = new GetPoductsUC(mySqlRepositoryQuery);
    }

    @Test
    void execute() {
        Product product = new Product(BranchId.of("id"), ProductId.of("idProduct"),
                new ProductName("name"), new ProductDescription("description"),new ProductPrice("500"),
                new ProductInventoryStock("5"),new ProductCategory("category"));

        when(mySqlRepositoryQuery.getAllProducts())
                .thenReturn(Flux.just(product));

        // Act
        Flux<Product> result = getPoductsUC.execute();

        StepVerifier.create(result)
                .expectNext(product)
                .verifyComplete();
        verify(mySqlRepositoryQuery, times(1)).getAllProducts();
    }
}