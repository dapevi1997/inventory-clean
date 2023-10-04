package co.com.inventory.usecase.alpha;

import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import reactor.core.publisher.Mono;

public class SaveUserViewUseCase {
    private final MySqlRepository mySqlRepository;
    public SaveUserViewUseCase(MySqlRepository mySqlRepository) {
        this.mySqlRepository = mySqlRepository;
    }
    public Mono<User> execute(String branchId, String userName, String userLastName, String userPassword, String userEmail, String userRol){
        return mySqlRepository.saveUser( branchId, userName,
                userLastName,
                userPassword,
                userEmail,
                userRol);
    }
}
