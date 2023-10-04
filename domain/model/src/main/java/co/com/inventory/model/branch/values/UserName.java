package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.exceptions.BlankStringException;

import java.util.Objects;

public class UserName {
    private String userName;

    public UserName(String userName) {
        Objects.requireNonNull(userName, "El campo userName no puede ser nulo");
        if(userName.isBlank()){
            throw new BlankStringException("El campo userName no puede estar vacío");
        }
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
