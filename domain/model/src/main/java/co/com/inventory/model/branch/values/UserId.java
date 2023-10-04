package co.com.inventory.model.branch.values;


import java.util.Objects;

public class UserId{
    private Long userId;
    public UserId(String userId) {
        Objects.requireNonNull(userId, "El campo userId no puede ser nulo");
        try {
            this.userId = Long.parseLong(userId);
        }catch (Exception e){
            throw new NumberFormatException("El campo userId debe ser un número entero");
        }
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public static UserId of(String uuid){
        return new UserId(uuid);
    }

}
