package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.exceptions.BlankStringException;

import java.util.Objects;

public class UserlastName {
    private String userLastName;

    public UserlastName(String userLastName) {
        Objects.requireNonNull(userLastName, "El campo userLastName no puede ser nulo");
        if(userLastName.isBlank()){
            throw new BlankStringException("El campo userLastName no puede estar vacío");
        }
        this.userLastName = userLastName;
    }

    public String getUserLastName() {
        return userLastName;
    }
}
