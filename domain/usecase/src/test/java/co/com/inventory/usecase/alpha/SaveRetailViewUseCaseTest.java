package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class SaveRetailViewUseCaseTest {
    private MySqlRepository mySqlRepository;
    private SaveRetailViewUseCase saveRetailViewUseCase;

    @BeforeEach
    void setUp() {
        mySqlRepository = mock(MySqlRepository.class);
        saveRetailViewUseCase = new SaveRetailViewUseCase(mySqlRepository);
    }

    @Test
    void execute() {
        List<ProductSale> productSaleList = new ArrayList<>();
        ProductSale productSale = new ProductSale(ProductSaleId.of("id"), new ProductSalePrice("500"),new ProductSaleStock("5"));
        productSaleList.add(productSale);

        when(mySqlRepository.saveSale(anyString(),anyList(),anyFloat(),anyString(),anyString()))
                .thenReturn(Flux.just(productSale));

        // Act
        Flux<ProductSale> result = saveRetailViewUseCase.execute("id",productSaleList,
                0.8F,"description","500");

        StepVerifier.create(result)
                .expectNext(productSale)
                .verifyComplete();
        verify(mySqlRepository, times(1)).saveSale(anyString(),anyList(),anyFloat(),anyString(),anyString());
    }
}