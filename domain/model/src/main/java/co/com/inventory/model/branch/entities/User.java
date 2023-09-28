package co.com.inventory.model.branch.entities;

import co.com.inventory.model.branch.generic.Entity;
import co.com.inventory.model.branch.values.*;

public class User extends Entity<UserId> {
    private UserName userName;
    private UserPassword userPassword;
    private UserEmail userEmail;
    private UserRole userRole;

    public User(UserId id, UserName userName, UserPassword userPassword, UserEmail userEmail, UserRole userRole) {
        super(id);
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userRole = userRole;
    }

    public UserName getUserName() {
        return userName;
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
