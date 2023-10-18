package co.com.inventory.api;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.model.branch.utils.SalesByBranchDTOModel;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.alpha.CreateBranchUseCase;
import co.com.inventory.usecase.alpha.comands.CreateBranchCommand;
import co.com.inventory.usecase.beta.*;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@ContextConfiguration(classes = {RouterRest.class, Handler.class})
//@WebFluxTest
class RouterRestQueryTest {

/*    @Autowired
    private WebTestClient webTestClient;*/

    @Test
    void testgetProductById() {

        GetProductByIdUC getProductByIdUC = mock(GetProductByIdUC.class);


        WebTestClient webTestClient = WebTestClient.bindToRouterFunction(new RouterRestQuery().getProductById(getProductByIdUC))
                .build();
        Product product = new Product(BranchId.of("id"), ProductId.of("idProduct"),
                new ProductName("name"), new ProductDescription("description"),new ProductPrice("500"),
                new ProductInventoryStock("5"),new ProductCategory("category"));

        when(getProductByIdUC.execute(anyString())).thenReturn(Mono.just(product));



        webTestClient
                .get()
                .uri("/api/v1/product/idProduct")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.productId").isEqualTo(product.getProductId().getProductId());


    }

    @Test
    void testgetProducts() {
        GetPoductsUC getPoductsUC = mock(GetPoductsUC.class);


        WebTestClient webTestClient = WebTestClient.bindToRouterFunction(new RouterRestQuery().getProducts(getPoductsUC))
                .build();
        Product product = new Product(BranchId.of("id"), ProductId.of("idProduct"),
                new ProductName("name"), new ProductDescription("description"),new ProductPrice("500"),
                new ProductInventoryStock("5"),new ProductCategory("category"));

        when(getPoductsUC.execute()).thenReturn(Flux.just(product));



        webTestClient
                .get()
                .uri("/api/v1/products")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].productId").isEqualTo(product.getProductId().getProductId());
    }

    @Test
    void testgetBranchs() {
        GetBranchsUC getBranchsUC = mock(GetBranchsUC.class);


        WebTestClient webTestClient = WebTestClient.bindToRouterFunction(new RouterRestQuery().getBranchs(getBranchsUC))
                .build();
        Branch branch = new Branch(BranchId.of("id"),new BranchName("name"),
                new BranchLocation("country","city"));

        when(getBranchsUC.execute()).thenReturn(Flux.just(branch));



        webTestClient
                .get()
                .uri("/api/v1/branchs")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].branchName").isEqualTo(branch.getBranchName().getBranchName());
    }

    @Test
    void testgetSalesbyBranchId() {
        GetSalesByBranchIdUC getSalesByBranchIdUC = mock(GetSalesByBranchIdUC.class);


        WebTestClient webTestClient = WebTestClient.bindToRouterFunction(new RouterRestQuery().getSalesbyBranchId(getSalesByBranchIdUC))
                .build();
        SalesByBranchDTOModel salesByBranchDTOModel = new SalesByBranchDTOModel();
        salesByBranchDTOModel.setBranchName("branchName");

        when(getSalesByBranchIdUC.execute(anyString())).thenReturn(Flux.just(salesByBranchDTOModel));



        webTestClient
                .get()
                .uri("/api/v1/sales/idBranch")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].branchName").isEqualTo(salesByBranchDTOModel.getBranchName());
    }

    @Test
    void testgetUserbyEmail() {

        GetUserByEmailUC getUserByEmailUC = mock(GetUserByEmailUC.class);


        WebTestClient webTestClient = WebTestClient.bindToRouterFunction(new RouterRestQuery().getUserbyEmail(getUserByEmailUC))
                .build();
        User user = new User(BranchId.of("idBranch"), UserId.of("id"),
                new UserName("name"), new UserlastName("lastname"),new UserPassword("password"),
                new UserEmail("user@user.com"),new UserRole("role"));

        when(getUserByEmailUC.execute(anyString())).thenReturn(Mono.just(user));



        webTestClient
                .get()
                .uri("/api/v1/user/user@user.com")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.userName.userName").isEqualTo(user.getUserName().getUserName());


    }
}
