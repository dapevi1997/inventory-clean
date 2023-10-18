package co.com.inventory.api;


import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.model.branch.exceptions.BlankStringException;
import co.com.inventory.usecase.alpha.*;
import co.com.inventory.usecase.alpha.comands.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {


    @RouterOperation(
            path = "/api/v1/branch/register",
            produces ={
                    MediaType.APPLICATION_JSON_VALUE
            },
            method = RequestMethod.POST,
            beanClass = CreateBranchCommand.class,
            beanMethod = "createBike",
            operation = @Operation(
                    operationId = "createBike",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "successful operation",
                                    content = @Content(schema = @Schema(
                                            implementation = BranchCreated.class
                                    ))
                            )
                    },
                    requestBody = @RequestBody(
                            content = @Content(schema = @Schema(
                                    implementation = BranchCreated.class
                            ))
                    )
            )
    )
    @Bean
    public RouterFunction<ServerResponse> routerFunction(CreateBranchUseCase createBranchUseCase) {
        return route(
                POST("/api/v1/branch/register")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    return createBranchUseCase.apply(request.bodyToMono(CreateBranchCommand.class))
                            .flatMap(domainEvent -> {
                                return ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(domainEvent));
                            })
                            .onErrorResume(Exception.class, e -> {
                                if (e instanceof NullPointerException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof BlankStringException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                return ServerResponse.badRequest().bodyValue(e.getMessage());
                            });

                }

        );
    }

    @Bean
    public RouterFunction<ServerResponse> addProduct(AddProductUseCase addProductUseCase) {
        return route(
                POST("/api/v1/product/register").and(accept(MediaType.APPLICATION_JSON)),

                request -> {
                    return addProductUseCase.apply(request.bodyToMono(AddProductCommand.class))
                            .flatMap(domainEvent -> {
                                return ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(domainEvent));
                            })
                            .onErrorResume(Exception.class, e -> {
                                if (e instanceof NullPointerException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof BlankStringException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof NumberFormatException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                return ServerResponse.badRequest().bodyValue(e.getMessage());
                            });

                }
        );
    }

    @Bean
    public RouterFunction<ServerResponse> registerUser(RegisterUserUseCase registerUserUseCase) {
        return route(
                POST("/api/v1/user/register").and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    return registerUserUseCase.apply(request.bodyToMono(AddUserCommand.class))
                            .flatMap(domainEvent -> {
                                return ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(domainEvent));
                            })
                            .onErrorResume(Exception.class, e -> {
                                if (e instanceof NullPointerException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof BlankStringException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof NumberFormatException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                return ServerResponse.badRequest().bodyValue(e.getMessage());
                            });
                }

        );
    }

    @Bean
    public RouterFunction<ServerResponse> registerSaleWholesale(RegisterSaleWholesaleUseCase registerSaleWholesaleUseCase) {

        return route(
                POST("/api/v1/sale/register/wholesale").and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    return registerSaleWholesaleUseCase.apply(request.bodyToMono(AddProductSaleCommand.class))
                            .flatMap(domainEvent -> {
                                return ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(domainEvent));
                            })
                            .onErrorResume(Exception.class, e -> {
                                if (e instanceof NullPointerException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof BlankStringException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof NumberFormatException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                return ServerResponse.badRequest().bodyValue(e.getMessage());
                            });
                }

        );
    }

    @Bean
    public RouterFunction<ServerResponse> registerSaleRetail(RegisterSaleRetailUseCase registerSaleRetailUseCase) {

        return route(
                POST("/api/v1/sale/register/retail").and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    return registerSaleRetailUseCase.apply(request.bodyToMono(AddProductSaleCommand.class))
                            .flatMap(domainEvent -> {
                                return ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(domainEvent));
                            })
                            .onErrorResume(Exception.class, e -> {
                                if (e instanceof NullPointerException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof BlankStringException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof NumberFormatException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                return ServerResponse.badRequest().bodyValue(e.getMessage());
                            });
                }

        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateProductInventory(UpdateProductUseCase updateProductUseCase) {

        return route(
                PUT("/api/v1/product/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    return updateProductUseCase.apply(request.bodyToMono(UpdateProductCommand.class))
                            .flatMap(domainEvent -> {
                                return ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(domainEvent));
                            })
                            .onErrorResume(Exception.class, e -> {
                                if (e instanceof NullPointerException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof BlankStringException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof NumberFormatException) {
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                return ServerResponse.badRequest().bodyValue(e.getMessage());
                            });
                }

        );
    }



}
