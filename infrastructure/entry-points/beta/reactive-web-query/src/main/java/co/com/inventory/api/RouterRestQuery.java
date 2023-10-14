package co.com.inventory.api;


import co.com.inventory.api.dtos.AuthResponse;
import co.com.inventory.api.dtos.ProductDTOResponse;
import co.com.inventory.api.utils.JwtServiceInWebQuery;
import co.com.inventory.api.utils.MapperMysqlQuery;
import co.com.inventory.api.utils.UserDetailUtil;
import co.com.inventory.model.branch.exceptions.BlankStringException;
import co.com.inventory.usecase.alpha.comands.LoginCommand;
import co.com.inventory.usecase.beta.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRestQuery {


    public RouterRestQuery() {

    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(GetProductByIdUC getProductByIdUC) {
        return route(
                GET("/api/v1/product/{id}")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    return getProductByIdUC.execute(request.pathVariable("id")).flatMap(
                            product -> {
                                ProductDTOResponse productDTOResponse = MapperMysqlQuery.ProdutToProductDTO(product);
                                return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(productDTOResponse);
                            }
                    ).switchIfEmpty(Mono.empty());
                    //TODO: caputar excepciones

                }

                );
    }

    @Bean
    public RouterFunction<ServerResponse> getProducts(GetPoductsUC getPoductsUC) {
        return route(
                GET("/api/v1/products")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    return getPoductsUC.execute().collectList()
                            .map(productList -> {
                               return MapperMysqlQuery.listProdutToListProductDTO(productList);
                            })
                            .flatMap(
                            productDTOResponseList -> {
                                return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(productDTOResponseList);
                            }
                    ).switchIfEmpty(ServerResponse.noContent().build());
                    //TODO: caputar excepciones

                }

        );
    }

    @Bean
    public RouterFunction<ServerResponse> getBranchs(GetBranchsUC getBranchsUC) {

        return route(
                GET("/api/v1/branchs")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    return getBranchsUC.execute()
                            .filter(branch -> {
                                return !branch.getBranchName().getBranchName().equals("Root");
                            })
                            .collectList()
                            .map(productList -> {
                                return MapperMysqlQuery.listBranchToListBranchDTO(productList);
                            })
                            .flatMap(
                                    branchDTOResponseList -> {
                                        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(branchDTOResponseList);
                                    }
                            ).switchIfEmpty(ServerResponse.noContent().build())
                            .onErrorResume(
                                    Exception.class, e -> {
                                        return ServerResponse.badRequest().bodyValue(e.getMessage());
                                    }
                            );


                }

        );
    }

    @Bean
    public RouterFunction<ServerResponse> getSalesbyBranchId(GetSalesByBranchIdUC getSalesByBranchIdUC) {

        return route(
                GET("/api/v1/sales/{branchId}")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    return getSalesByBranchIdUC.execute(request.pathVariable("branchId")).collectList()
                            .flatMap(
                                    salesByBranchDTOModelList -> {
                                        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(salesByBranchDTOModelList);
                                    }
                            ).switchIfEmpty(ServerResponse.noContent().build());
                    //TODO: caputar excepciones

                }

        );
    }

    @Bean
    public RouterFunction<ServerResponse> getUserbyEmail(GetUserByEmailUC getUserByEmailUC) {

        return route(
                GET("/api/v1/user/{email}")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    return getUserByEmailUC.execute(request.pathVariable("email"))
                            .flatMap(user -> {
                                if (Objects.isNull(user)){
                                    ServerResponse.noContent().build();
                                }
                                return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(user);
                            }

                            );
                    //TODO: caputar excepciones

                }

        );
    }



}
