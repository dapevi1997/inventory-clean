package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserEmail implements ValueObject<String> {
    private String userEmail;

    public UserEmail(String userEmail) {
        Objects.requireNonNull(userEmail, "El campo userEmail no puede ser nulo");

            Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
            Matcher matcher = pattern.matcher(userEmail);
            if (!matcher.matches()){
                throw new IllegalArgumentException("Formato de email inválido");
            }


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
