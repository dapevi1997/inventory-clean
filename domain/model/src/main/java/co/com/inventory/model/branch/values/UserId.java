package co.com.inventory.model.branch.values;


import co.com.inventory.model.branch.exceptions.BlankStringException;

import java.util.Objects;

public class UserId{
    private String userId;
    public UserId(String userId) {
        Objects.requireNonNull(userId, "El campo userId no puede ser nulo");
        if(userId.isBlank()){
            throw new BlankStringException("El campo userId no puede estar vacío");
        }
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static UserId of(String uuid){
        return new UserId(uuid);
    }

}
