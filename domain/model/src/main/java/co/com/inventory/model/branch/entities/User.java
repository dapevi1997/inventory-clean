package co.com.inventory.model.branch.entities;

import co.com.inventory.model.branch.values.*;

import java.util.Objects;

public class User {
    private BranchId branchId;
    private UserId userId;
    private UserName userName;
    private UserlastName userlastName;
    private UserPassword userPassword;
    private UserEmail userEmail;
    private UserRole userRole;

    public User(BranchId branchId, UserId userId, UserName userName, UserlastName userlastName, UserPassword userPassword, UserEmail userEmail, UserRole userRole) {
        this.branchId = branchId;
        Objects.requireNonNull(userId, "El UserId no puede ser nulo");
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.userlastName = userlastName;
    }

    public void setBranchId(BranchId branchId) {
        this.branchId = branchId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public void setUserlastName(UserlastName userlastName) {
        this.userlastName = userlastName;
    }

    public void setUserPassword(UserPassword userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserEmail(UserEmail userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserId getUserId() {
        return userId;
    }

    public BranchId getBranchId() {
        return branchId;
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
