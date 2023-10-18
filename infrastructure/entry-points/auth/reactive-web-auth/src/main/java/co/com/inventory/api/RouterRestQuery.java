package co.com.inventory.api;


import co.com.inventory.api.dtos.AuthResponse;
import co.com.inventory.usecase.auth.LoginUsC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;




import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRestQuery {


   // @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "login")})
   @RouterOperation(
           path = "/api/v1/auth/login",
           produces = {
                   MediaType.APPLICATION_JSON_VALUE
           },
           method = RequestMethod.POST,
           beanClass = LoginUsC.class,
           beanMethod = "listenPOSTLoginUser",
           operation = @Operation(
                   operationId = "listenPOSTLoginUser",
                   responses = {
                           @ApiResponse(
                                   responseCode = "200",
                                   description = "Producto creado",
                                   content = @Content(schema = @Schema(
                                           implementation = AuthResponse.class
                                   )
                                   )
                           )
                   }
           )
   )
    @Bean
    public RouterFunction<ServerResponse> loginUser(Handler handler){
        return route(POST("/api/v1/auth/login").and(accept(MediaType.APPLICATION_JSON)),
                handler::listenPOSTLoginUser);
    }



}
