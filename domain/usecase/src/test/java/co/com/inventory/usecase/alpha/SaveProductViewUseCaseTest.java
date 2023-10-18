package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class SaveProductViewUseCaseTest {
    private MySqlRepository mySqlRepository;
    private SaveProductViewUseCase saveProductViewUseCase;

    @BeforeEach
    void setUp() {
        mySqlRepository = mock(MySqlRepository.class);
        saveProductViewUseCase = new SaveProductViewUseCase(mySqlRepository);
    }

    @Test
    void execute() {
        Product product = new Product(BranchId.of("id"), ProductId.of("idProduct"),
                new ProductName("name"), new ProductDescription("description"),new ProductPrice("500"),
                new ProductInventoryStock("5"),new ProductCategory("category"));

        when(mySqlRepository.saveProduct(anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString()))
                .thenReturn(Mono.just(product));

        // Act
        Mono<Product> result = saveProductViewUseCase.execute("id","idProduct",
                "name","description","500","5","category");

        StepVerifier.create(result)
                .expectNext(product)
                .verifyComplete();
        verify(mySqlRepository, times(1)).saveProduct(anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString());
    }
}