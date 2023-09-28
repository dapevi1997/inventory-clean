package co.com.inventory.api;


import co.com.inventory.model.branch.generic.DomainEvent;
import co.com.inventory.usecase.addproduct.AddProductUseCase;
import co.com.inventory.usecase.createbranch.CreateBranchUseCase;
import co.com.inventory.usecase.generic.commands.AddProductCommand;
import co.com.inventory.usecase.generic.commands.AddUserCommand;
import co.com.inventory.usecase.generic.commands.CreateBranchCommand;
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
                POST("/api/createBranch")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(
                                BodyInserters.fromPublisher(
                                createBranchUseCase.apply(request.bodyToMono(CreateBranchCommand.class)), DomainEvent.class
                                )
                        )

                );
    }

    @Bean
    public RouterFunction<ServerResponse> addProduct(AddProductUseCase addProductUseCase){
        return route(
                POST("/api/addProduct").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(addProductUseCase.apply(
                                request.bodyToMono(AddProductCommand.class)
                        ), DomainEvent.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> registerUser(RegisterUserUseCase registerUserUseCase){
        return route(
                POST("/api/registerUser").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(registerUserUseCase.apply(
                                request.bodyToMono(AddUserCommand.class)
                        ), DomainEvent.class))
        );
    }

}
