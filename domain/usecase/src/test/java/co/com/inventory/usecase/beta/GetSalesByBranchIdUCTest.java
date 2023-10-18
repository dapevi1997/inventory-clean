package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.utils.SalesByBranchDTOModel;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class GetSalesByBranchIdUCTest {
    private MySqlRepositoryQuery mySqlRepositoryQuery;
    private GetSalesByBranchIdUC getSalesByBranchIdUC;

    @BeforeEach
    void setUp() {
        mySqlRepositoryQuery = mock(MySqlRepositoryQuery.class);
        getSalesByBranchIdUC = new GetSalesByBranchIdUC(mySqlRepositoryQuery);

    }

    @Test
    void execute() {
        SalesByBranchDTOModel salesByBranchDTOModel = new SalesByBranchDTOModel();
        salesByBranchDTOModel.setBranchName("nameBranch");
        salesByBranchDTOModel.setProductName("productName");
        salesByBranchDTOModel.setProductPrice(500F);
        salesByBranchDTOModel.setProductSalePrice(400F);
        salesByBranchDTOModel.setProductSaleAmount(4);
        salesByBranchDTOModel.setSaleDate("14/2/23");
        salesByBranchDTOModel.setSaleType("wholesale");
        salesByBranchDTOModel.setSaleUser("user");

        when(mySqlRepositoryQuery.getSalesByBranchId(anyString()))
                .thenReturn(Flux.just(salesByBranchDTOModel));

        // Act
        Flux<SalesByBranchDTOModel> result = getSalesByBranchIdUC.execute("id");

        StepVerifier.create(result)
                .expectNext(salesByBranchDTOModel)
                .verifyComplete();
        verify(mySqlRepositoryQuery, times(1)).getSalesByBranchId(anyString());
    }
}