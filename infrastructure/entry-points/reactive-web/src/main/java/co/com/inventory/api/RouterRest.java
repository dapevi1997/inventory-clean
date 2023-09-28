package co.com.inventory.api;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.model.branch.generic.DomainEvent;
import co.com.inventory.usecase.createbranch.CreateBranchUseCase;
import co.com.inventory.usecase.generic.commands.CreateBranchCommand;
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
}
