package co.com.inventory.api;


import co.com.inventory.api.dtos.AuthResponse;
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
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;




import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRestQuery {


   @RouterOperation(
           path = "/api/v1/auth/login",
           produces = {
                   MediaType.APPLICATION_JSON_VALUE
           },
           method = RequestMethod.POST,
           beanClass = Handler.class,
           beanMethod = "listenPOSTLoginUser",
           operation = @Operation(
                   operationId = "listenPOSTLoginUser",
                   requestBody = @RequestBody(content = @Content(schema = @Schema(
                           implementation = AuthRequest.class
                   )))
                   ,
                   responses = {
                           @ApiResponse(
                                   responseCode = "200",
                                   description = "Login",
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
