package co.com.inventory.model.branch.entities;

import co.com.inventory.model.branch.generic.Entity;
import co.com.inventory.model.branch.values.*;

import java.util.Objects;

public class User extends Entity<UserId> {
    private UserName userName;
    private UserlastName userlastName;
    private UserPassword userPassword;
    private UserEmail userEmail;
    private UserRole userRole;

    public User(UserId id, UserName userName, UserlastName userlastName,UserPassword userPassword, UserEmail userEmail, UserRole userRole) {
        super(id);
        Objects.requireNonNull(id, "El UserId no puede ser nulo");
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.userlastName = userlastName;
    }

    public UserName getUserName() {
        return userName;
    }

    public UserlastName getUserlastName() {
        return userlastName;
    }

    public UserPassword getUserPassword() {
        return userPassword;
    }

    public UserEmail getUserEmail() {
        return userEmail;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
