package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserPassword implements ValueObject<String> {
    private String password;

    public UserPassword(String password) {
        Objects.requireNonNull(password, "El campo password no puede ser nulo");
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z0-9]).{8,16}$");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()){
            throw new IllegalArgumentException("La contraseña debe ser de mínimo 8 caracteres y máximo de 16");
        }

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
