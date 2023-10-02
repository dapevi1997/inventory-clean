package co.com.inventory.api;


import co.com.inventory.model.branch.exceptions.BlankStringException;
import co.com.inventory.model.branch.generic.DomainEvent;
import co.com.inventory.usecase.addproduct.AddProductUseCase;
import co.com.inventory.usecase.createbranch.CreateBranchUseCase;
import co.com.inventory.usecase.generic.commands.AddProductCommand;
import co.com.inventory.usecase.generic.commands.AddProductSaleCommand;
import co.com.inventory.usecase.generic.commands.AddUserCommand;
import co.com.inventory.usecase.generic.commands.CreateBranchCommand;
import co.com.inventory.usecase.registersaleretail.RegisterSaleRetailUseCase;
import co.com.inventory.usecase.registersalewholesale.RegisterSaleWholesaleUseCase;
import co.com.inventory.usecase.registeruser.RegisterUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
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
                            }).next()
                            .onErrorResume(Exception.class, e -> {
                                if(e instanceof NullPointerException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof BlankStringException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                return ServerResponse.badRequest().bodyValue(e.getMessage());
                            });

                }

                );
    }

    @Bean
    public RouterFunction<ServerResponse> addProduct(AddProductUseCase addProductUseCase){
        return route(
                POST("/api/v1/product/register").and(accept(MediaType.APPLICATION_JSON)),

                request -> {
                    return addProductUseCase.apply(request.bodyToMono(AddProductCommand.class))
                            .flatMap(domainEvent -> {
                                return ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(domainEvent));
                            }).next()
                            .onErrorResume(Exception.class, e -> {
                                if(e instanceof NullPointerException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof BlankStringException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof NumberFormatException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                return ServerResponse.badRequest().bodyValue(e.getMessage());
                            });

                }
        );
    }

    @Bean
    public RouterFunction<ServerResponse> registerUser(RegisterUserUseCase registerUserUseCase){
        return route(
                POST("/api/v1/user/register").and(accept(MediaType.APPLICATION_JSON)),
                request ->{
                    return registerUserUseCase.apply(request.bodyToMono(AddUserCommand.class))
                            .flatMap(domainEvent -> {
                                return ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(domainEvent));
                            }).next()
                            .onErrorResume(Exception.class, e -> {
                                if(e instanceof NullPointerException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof BlankStringException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof NumberFormatException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                return ServerResponse.badRequest().bodyValue(e.getMessage());
                            });
                }

        );
    }

    @Bean
    public RouterFunction<ServerResponse> registerSaleWholesale(RegisterSaleWholesaleUseCase registerSaleWholesaleUseCase){

        return route(
                POST("/api/v1/sale/register/wholesale").and(accept(MediaType.APPLICATION_JSON)),
                request ->{
                    return registerSaleWholesaleUseCase.apply(request.bodyToMono(AddProductSaleCommand.class))
                            .flatMap(domainEvent -> {
                                return ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(domainEvent));
                            }).next()
                            .onErrorResume(Exception.class, e -> {
                                if(e instanceof NullPointerException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof BlankStringException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof NumberFormatException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                return ServerResponse.badRequest().bodyValue(e.getMessage());
                            });
                }

        );
    }

    @Bean
    public RouterFunction<ServerResponse> registerSaleRetail(RegisterSaleRetailUseCase registerSaleRetailUseCase){

        return route(
                POST("/api/v1/sale/register/retail").and(accept(MediaType.APPLICATION_JSON)),
                request ->{
                    return registerSaleRetailUseCase.apply(request.bodyToMono(AddProductSaleCommand.class))
                            .flatMap(domainEvent -> {
                                return ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(domainEvent));
                            }).next()
                            .onErrorResume(Exception.class, e -> {
                                if(e instanceof NullPointerException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof BlankStringException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof NumberFormatException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                return ServerResponse.badRequest().bodyValue(e.getMessage());
                            });
                }

        );
    }

}
