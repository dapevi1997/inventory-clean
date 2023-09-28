package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.Identity;

public class UserId extends Identity {
    public UserId() {
    }
    public UserId(String uuid) {
        super(uuid);
    }

    public static UserId of(String uuid){
        return new UserId(uuid);
    }

}
