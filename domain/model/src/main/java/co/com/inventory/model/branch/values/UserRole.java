package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.exceptions.BlankStringException;
import co.com.inventory.model.branch.generic.ValueObject;

import java.util.Objects;

public class UserRole implements ValueObject<String> {
    private String userRole;

    public UserRole(String userRole) {
        Objects.requireNonNull(userRole, "El campo userRole no puede ser nulo");
        if(userRole.isBlank()){
            throw new BlankStringException("El campo userRole no puede estar vacío");
        }
        this.userRole = userRole;
    }

    @Override
    public String value() {
        return userRole;
    }

    public String getUserRole() {
        return userRole;
    }
}
