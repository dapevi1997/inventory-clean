package co.com.inventory.usecase.beta;

import co.com.inventory.model.branch.entities.User;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import reactor.core.publisher.Mono;

public class SaveUserViewUC {
    private final MySqlRepositoryQuery mySqlRepositoryQuery;
    public SaveUserViewUC(MySqlRepositoryQuery mySqlRepositoryQuery) {

        this.mySqlRepositoryQuery = mySqlRepositoryQuery;
    }
    public Mono<User> execute(String branchId, String userName, String userLastName, String userPassword, String userEmail, String userRol){
        return mySqlRepositoryQuery.saveUser( branchId, userName,
                userLastName,
                userPassword,
                userEmail,
                userRol);
    }
}
