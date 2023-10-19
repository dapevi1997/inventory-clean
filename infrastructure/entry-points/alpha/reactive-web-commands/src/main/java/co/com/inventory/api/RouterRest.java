package co.com.inventory.api;


import co.com.inventory.api.dtos.AuthResponse;
import co.com.inventory.model.branch.events.*;
import co.com.inventory.model.branch.exceptions.BlankStringException;
import co.com.inventory.usecase.alpha.*;
import co.com.inventory.usecase.alpha.comands.*;
import co.com.inventory.usecase.utils.AuthRequest;
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
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            method = RequestMethod.POST,
            beanClass = RouterRest.class,
            beanMethod = "createBranch",
            operation = @Operation(
                    operationId = "createBranch",
                    requestBody = @RequestBody(content = @Content(schema = @Schema(
                            implementation = CreateBranchCommand.class
                    )))
                    ,
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "BranchCrated",
                                    content = @Content(schema = @Schema(
                                            implementation = BranchCreated.class
                                    )
                                    )
                            )
                    }
            )
    )
    @Bean
    public RouterFunction<ServerResponse> createBranch(CreateBranchUseCase createBranchUseCase) {
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

    @RouterOperation(
            path = "/api/v1/product/register",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            method = RequestMethod.POST,
            beanClass = RouterRest.class,
            beanMethod = "addProduct",
            operation = @Operation(
                    operationId = "addProduct",
                    requestBody = @RequestBody(content = @Content(schema = @Schema(
                            implementation = AddProductCommand.class
                    )))
                    ,
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Product Added",
                                    content = @Content(schema = @Schema(
                                            implementation = ProductAdded.class
                                    )
                                    )
                            )
                    }
            )
    )
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

    @RouterOperation(
            path = "/api/v1/user/register",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            method = RequestMethod.POST,
            beanClass = RouterRest.class,
            beanMethod = "registerUser",
            operation = @Operation(
                    operationId = "registerUser",
                    requestBody = @RequestBody(content = @Content(schema = @Schema(
                            implementation = AddUserCommand.class
                    )))
                    ,
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "User Added",
                                    content = @Content(schema = @Schema(
                                            implementation = UserRegistered.class
                                    )
                                    )
                            )
                    }
            )
    )
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

    @RouterOperation(
            path = "/api/v1/sale/register/wholesale",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            method = RequestMethod.POST,
            beanClass = RouterRest.class,
            beanMethod = "registerSaleWholesale",
            operation = @Operation(
                    operationId = "registerSaleWholesale",
                    requestBody = @RequestBody(content = @Content(schema = @Schema(
                            implementation = AddProductSaleCommand.class
                    )))
                    ,
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Wholesale",
                                    content = @Content(schema = @Schema(
                                            implementation = ProductSoldWholesale.class
                                    )
                                    )
                            )
                    }
            )
    )
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

    @RouterOperation(
            path = "/api/v1/sale/register/retail",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            method = RequestMethod.POST,
            beanClass = RouterRest.class,
            beanMethod = "registerSaleRetail",
            operation = @Operation(
                    operationId = "registerSaleRetail",
                    requestBody = @RequestBody(content = @Content(schema = @Schema(
                            implementation = AddProductSaleCommand.class
                    )))
                    ,
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Retail",
                                    content = @Content(schema = @Schema(
                                            implementation = ProductSoldRetail.class
                                    )
                                    )
                            )
                    }
            )
    )
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

    @RouterOperation(
            path = "/api/v1/product/update",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            method = RequestMethod.PUT,
            beanClass = RouterRest.class,
            beanMethod = "updateProductInventory",
            operation = @Operation(
                    operationId = "updateProductInventory",
                    requestBody = @RequestBody(content = @Content(schema = @Schema(
                            implementation = UpdateProductCommand.class
                    )))
                    ,
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Product Update",
                                    content = @Content(schema = @Schema(
                                            implementation = ProductUpdated.class
                                    )
                                    )
                            )
                    }
            )
    )
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
