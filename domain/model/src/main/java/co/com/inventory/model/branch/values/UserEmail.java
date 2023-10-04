package co.com.inventory.model.branch.values;


import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserEmail {
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

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
