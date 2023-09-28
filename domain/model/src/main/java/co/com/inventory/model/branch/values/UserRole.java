package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

public class UserRole implements ValueObject<String> {
    private String userRole;

    public UserRole(String userRole) {
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
