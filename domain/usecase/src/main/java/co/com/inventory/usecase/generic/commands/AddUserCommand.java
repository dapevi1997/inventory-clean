package co.com.inventory.usecase.generic.commands;

import co.com.inventory.usecase.generic.Command;

public class AddUserCommand extends Command {
    private String branchId;
    private String userName;
    private String userLastname;
    private String userPassword;
    private String userEmail;
    private String userRole;

    public AddUserCommand() {
    }
    public AddUserCommand(String branchId, String userName, String userLastname, String userPassword, String userEmail, String userRole) {
        this.branchId = branchId;
        this.userName = userName;
        this.userLastname = userLastname;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userRole = userRole;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLastname() {
        return userLastname;
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
