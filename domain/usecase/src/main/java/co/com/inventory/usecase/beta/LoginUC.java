package co.com.inventory.usecase.beta;


import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.events.ProductAdded;
import co.com.inventory.model.branch.utils.DomainEvent;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.usecase.alpha.comands.LoginCommand;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class LoginUC {

    private  final MySqlRepositoryQuery mySqlRepositoryQuery;


    public LoginUC(MySqlRepositoryQuery mySqlRepositoryQuery) {
        this.mySqlRepositoryQuery = mySqlRepositoryQuery;
    }

    public Mono<User> apply(Mono<LoginCommand> loginCommandMono) {
        return loginCommandMono.flatMap(loginCommand -> {
            return mySqlRepositoryQuery.findUserByEmail(loginCommand.getEmail());
                }
          );

    }
}
