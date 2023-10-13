package co.com.inventory.api;

import co.com.inventory.usecase.alpha.comands.LoginCommand;
import co.com.inventory.usecase.auth.LoginUsC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private final LoginUsC loginUsC;
    public Mono<ServerResponse> listenPOSTLoginUser(ServerRequest serverRequest) {

        return loginUsC.apply(serverRequest.bodyToMono(LoginCommand.class))
                .flatMap(domainEvent -> {
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(domainEvent));
                })
                .onErrorResume(Exception.class, e -> {
                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                });
    }
}
