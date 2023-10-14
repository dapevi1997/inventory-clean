package co.com.inventory.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class GlobalExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof io.jsonwebtoken.ExpiredJwtException) {
            // Maneja la excepción ExpiredJwtException aquí
            return handleExpiredJwtException(exchange);
        }
        return Mono.error(ex);
    }

    private Mono<Void> handleExpiredJwtException(ServerWebExchange exchange) {
        // Configura el estado y tipo de contenido de la respuesta
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // Crea un mensaje de error
        String errorMessage = "JWTExpired";

        // Escribe el mensaje en el cuerpo de la respuesta
        byte[] errorMessageBytes = errorMessage.getBytes();
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(errorMessageBytes)));
    }

}
