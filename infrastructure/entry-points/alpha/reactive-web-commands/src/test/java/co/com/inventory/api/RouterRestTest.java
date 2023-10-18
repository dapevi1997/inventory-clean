package co.com.inventory.api;

import co.com.inventory.model.branch.events.*;
import co.com.inventory.model.branch.utils.ProductSaleUtil;
import co.com.inventory.usecase.alpha.*;
import co.com.inventory.usecase.alpha.comands.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@ContextConfiguration(classes = {RouterRest.class})
//@WebFluxTest
class RouterRestTest {

/*    @Autowired
    private WebTestClient webTestClient;*/

    @Test
    void testCreateBranch() {

        CreateBranchUseCase createBranchUseCase = mock(CreateBranchUseCase.class);

        WebTestClient webTestClient = WebTestClient.bindToRouterFunction(new RouterRest().createBranch(createBranchUseCase))
                        .build();

        CreateBranchCommand requestBody = new CreateBranchCommand("name","country","city");
        BranchCreated branchCreated = new BranchCreated("name","country","city");

        when(createBranchUseCase.apply(any())).thenReturn(Mono.just(branchCreated));

        WebTestClient.ResponseSpec response = webTestClient.post()
                .uri("/api/v1/branch/register")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestBody), BranchCreated.class)
                .exchange()
                .expectStatus().isOk();

                response.expectBody()
                .jsonPath("$.branchName").isEqualTo(branchCreated.getBranchName());


    }


    @Test
    void testAddProduct() {
        AddProductUseCase addProductUseCase = mock(AddProductUseCase.class);

        WebTestClient webTestClient = WebTestClient.bindToRouterFunction(new RouterRest().addProduct(addProductUseCase))
                .build();

        AddProductCommand requestBody = new AddProductCommand("idBranch","name","description","500","5","category");
        ProductAdded productAdded = new ProductAdded("idBranch","idProduct","name","description","500","5","category");

        when(addProductUseCase.apply(any())).thenReturn(Mono.just(productAdded));

        WebTestClient.ResponseSpec response = webTestClient.post()
                .uri("/api/v1/product/register")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestBody), ProductAdded.class)
                .exchange()
                .expectStatus().isOk();

        response.expectBody()
                .jsonPath("$.productName").isEqualTo(productAdded.getProductName());

    }

    @Test
    void testregisterUser() {
        RegisterUserUseCase registerUserUseCase = mock(RegisterUserUseCase.class);

        WebTestClient webTestClient = WebTestClient.bindToRouterFunction(new RouterRest().registerUser(registerUserUseCase))
                .build();

        AddUserCommand requestBody = new AddUserCommand("idBranch","name","lastname","password","user@user.com","role");
        UserRegistered userRegistered = new UserRegistered("idBranch","idUser","name","lastname","password","user@user.com","role");

        when(registerUserUseCase.apply(any())).thenReturn(Mono.just(userRegistered));

        WebTestClient.ResponseSpec response = webTestClient.post()
                .uri("/api/v1/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestBody), UserRegistered.class)
                .exchange()
                .expectStatus().isOk();

        response.expectBody()
                .jsonPath("$.userName").isEqualTo(userRegistered.getUserName());
    }

    @Test
    void testregisterSaleWholesale() {
        RegisterSaleWholesaleUseCase registerSaleWholesaleUseCase = mock(RegisterSaleWholesaleUseCase.class);

        WebTestClient webTestClient = WebTestClient.bindToRouterFunction(new RouterRest().registerSaleWholesale(registerSaleWholesaleUseCase))
                .build();

        ProductSaleUtil productSaleUtil = new ProductSaleUtil("saleId","5");
        List<ProductSaleUtil> productSalesUtil = new ArrayList<>();
        productSalesUtil.add(productSaleUtil);

        AddProductSaleCommand requestBody = new AddProductSaleCommand("idBranch","saleId",productSalesUtil);

        ProductSoldWholesale productSoldWholesale = new ProductSoldWholesale("idBranch","saleId","sales");

        when(registerSaleWholesaleUseCase.apply(any())).thenReturn(Mono.just(productSoldWholesale));

        WebTestClient.ResponseSpec response = webTestClient.post()
                .uri("/api/v1/sale/register/wholesale")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestBody), ProductSoldWholesale.class)
                .exchange()
                .expectStatus().isOk();

        response.expectBody()
                .jsonPath("$.branchId").isEqualTo(productSoldWholesale.getBranchId());
    }

    @Test
    void testregisterSaleRetail() {
        RegisterSaleRetailUseCase registerSaleRetailUseCase = mock(RegisterSaleRetailUseCase.class);

        WebTestClient webTestClient = WebTestClient.bindToRouterFunction(new RouterRest().registerSaleRetail(registerSaleRetailUseCase))
                .build();

        ProductSaleUtil productSaleUtil = new ProductSaleUtil("saleId","5");
        List<ProductSaleUtil> productSalesUtil = new ArrayList<>();
        productSalesUtil.add(productSaleUtil);

        AddProductSaleCommand requestBody = new AddProductSaleCommand("idBranch","saleId",productSalesUtil);

        ProductSoldRetail productSoldRetail = new ProductSoldRetail("idBranch","saleId","sales");

        when(registerSaleRetailUseCase.apply(any())).thenReturn(Mono.just(productSoldRetail));

        WebTestClient.ResponseSpec response = webTestClient.post()
                .uri("/api/v1/sale/register/retail")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestBody), ProductSoldRetail.class)
                .exchange()
                .expectStatus().isOk();

        response.expectBody()
                .jsonPath("$.branchId").isEqualTo(productSoldRetail.getBranchId());
    }

    @Test
    void testupdateProductInventory() {
        UpdateProductUseCase updateProductUseCase = mock(UpdateProductUseCase.class);

        WebTestClient webTestClient = WebTestClient.bindToRouterFunction(new RouterRest().updateProductInventory(updateProductUseCase))
                .build();

        UpdateProductCommand requestBody = new UpdateProductCommand("id",5);

        ProductUpdated productUpdated = new ProductUpdated("id",5);

        when(updateProductUseCase.apply(any())).thenReturn(Mono.just(productUpdated));

        WebTestClient.ResponseSpec response = webTestClient.put()
                .uri("/api/v1/product/update")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestBody), ProductUpdated.class)
                .exchange()
                .expectStatus().isOk();

        response.expectBody()
                .jsonPath("$.idProduct").isEqualTo(productUpdated.getIdProduct());
    }
}
