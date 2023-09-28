package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

public class UserPassword implements ValueObject<String> {
    private String password;

    public UserPassword(String password) {
        this.password = password;
    }

    @Override
    public String value() {
        return password;
    }

    public String getPassword() {
        return password;
    }
}
