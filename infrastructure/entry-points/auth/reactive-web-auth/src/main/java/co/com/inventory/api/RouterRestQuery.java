package co.com.inventory.api;


import co.com.inventory.api.dtos.AuthResponse;
import co.com.inventory.api.dtos.ProductDTOResponse;
import co.com.inventory.api.utils.JwtServiceInWebQuery;
import co.com.inventory.api.utils.MapperMysqlQuery;
import co.com.inventory.api.utils.UserDetailUtil;
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



    @Bean
    public RouterFunction<ServerResponse> loginUser(Handler handler){
        return route(POST("/api/v1/auth/login").and(accept(MediaType.APPLICATION_JSON)),
                handler::listenPOSTLoginUser);
    }



}
