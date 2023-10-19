package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.values.ProductSaleId;
import co.com.inventory.model.branch.values.ProductSalePrice;
import co.com.inventory.model.branch.values.ProductSaleStock;
import co.com.inventory.usecase.beta.SaveWholesaleViewUC;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class SaveWholesaleViewUCTest {
    private MySqlRepositoryQuery mySqlRepositoryQuery;
    private SaveWholesaleViewUC saveWholesaleViewUC;

    @BeforeEach
    void setUp() {
        mySqlRepositoryQuery = mock(MySqlRepositoryQuery.class);
        saveWholesaleViewUC = new SaveWholesaleViewUC(mySqlRepositoryQuery);
    }

    @Test
    void execute() {
        List<ProductSale> productSaleList = new ArrayList<>();
        ProductSale productSale = new ProductSale(ProductSaleId.of("id"), new ProductSalePrice("500"),new ProductSaleStock("5"));
        productSaleList.add(productSale);

        when(mySqlRepositoryQuery.saveSale(anyString(),anyList(),anyFloat(),anyString(),anyString()))
                .thenReturn(Flux.just(productSale));

        // Act
        Flux<ProductSale> result = saveWholesaleViewUC.execute("id",productSaleList,
                0.7F,"description","500");

        StepVerifier.create(result)
                .expectNext(productSale)
                .verifyComplete();
        verify(mySqlRepositoryQuery, times(1)).saveSale(anyString(),anyList(),anyFloat(),anyString(),anyString());
    }
}