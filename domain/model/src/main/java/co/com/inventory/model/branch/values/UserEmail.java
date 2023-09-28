package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

public class UserEmail implements ValueObject<String> {
    private String userEmail;

    public UserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String value() {
        return userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
