package co.com.inventory.api;


import co.com.inventory.usecase.beta.GetPriceByIdUC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRestQuery {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(GetPriceByIdUC getPriceByIdUC) {
        return route(
                GET("/api/v1/product/{id}")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    return getPriceByIdUC.execute(request.pathVariable("id")).flatMap(
                            id -> {
                                return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(id);
                            }
                    ).switchIfEmpty(Mono.empty());
        /*            return createBranchUseCase.apply(request.bodyToMono(CreateBranchCommand.class))
                            .flatMap(domainEvent -> {
                                return ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(domainEvent));
                            })
                            .onErrorResume(Exception.class, e -> {
                                if(e instanceof NullPointerException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                if (e instanceof BlankStringException){
                                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                                }
                                return ServerResponse.badRequest().bodyValue(e.getMessage());
                            });*/

                }

                );
    }

}
