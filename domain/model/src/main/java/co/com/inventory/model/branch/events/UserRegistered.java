package co.com.inventory.model.branch.events;

import co.com.inventory.model.branch.generic.DomainEvent;

public class UserRegistered extends DomainEvent {
    private String userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userRole;

    public UserRegistered() {
        super("co.com.inventory.model.branch.events.UserRegistered");
    }

    public UserRegistered(String userId,String userName, String userPassword, String userEmail, String userRole) {
        super("co.com.inventory.model.branch.events.UserRegistered");
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userRole = userRole;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserRole() {
        return userRole;
    }
}
