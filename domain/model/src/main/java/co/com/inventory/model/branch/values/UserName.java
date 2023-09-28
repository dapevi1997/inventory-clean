package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

public class UserName implements ValueObject<String> {
    private String userName;

    public UserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String value() {
        return userName;
    }

    public String getUserName() {
        return userName;
    }
}
