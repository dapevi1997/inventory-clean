package co.com.inventory.usecase.alpha;

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

class UpdateProductViewUseCaseTest {
    private MySqlRepository mySqlRepository;
    private UpdateProductViewUseCase updateProductViewUseCase;

    @BeforeEach
    void setUp() {
        mySqlRepository = mock(MySqlRepository.class);
        updateProductViewUseCase = new UpdateProductViewUseCase(mySqlRepository);
    }

    @Test
    void execute() {
        Product product = new Product(BranchId.of("id"), ProductId.of("idProduct"),
                new ProductName("name"), new ProductDescription("description"),new ProductPrice("500"),
                new ProductInventoryStock("5"),new ProductCategory("category"));

        when(mySqlRepository.updateProductStock(anyString(),anyInt()))
                .thenReturn(Mono.just(product));

        // Act
        Mono<Product> result = updateProductViewUseCase.execute("idProduct",5);

        StepVerifier.create(result)
                .expectNext(product)
                .verifyComplete();
        verify(mySqlRepository, times(1)).updateProductStock(anyString(),anyInt());
    }
}