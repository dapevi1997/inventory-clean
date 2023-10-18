package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.values.ProductSaleId;
import co.com.inventory.model.branch.values.ProductSalePrice;
import co.com.inventory.model.branch.values.ProductSaleStock;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class SaveWholesaleViewUseCaseTest {
    private MySqlRepository mySqlRepository;
    private SaveWholesaleViewUseCase saveWholesaleViewUseCase;

    @BeforeEach
    void setUp() {
        mySqlRepository = mock(MySqlRepository.class);
        saveWholesaleViewUseCase = new SaveWholesaleViewUseCase(mySqlRepository);
    }

    @Test
    void execute() {
        List<ProductSale> productSaleList = new ArrayList<>();
        ProductSale productSale = new ProductSale(ProductSaleId.of("id"), new ProductSalePrice("500"),new ProductSaleStock("5"));
        productSaleList.add(productSale);

        when(mySqlRepository.saveSale(anyString(),anyList(),anyFloat(),anyString(),anyString()))
                .thenReturn(Flux.just(productSale));

        // Act
        Flux<ProductSale> result = saveWholesaleViewUseCase.execute("id",productSaleList,
                0.7F,"description","500");

        StepVerifier.create(result)
                .expectNext(productSale)
                .verifyComplete();
        verify(mySqlRepository, times(1)).saveSale(anyString(),anyList(),anyFloat(),anyString(),anyString());
    }
}