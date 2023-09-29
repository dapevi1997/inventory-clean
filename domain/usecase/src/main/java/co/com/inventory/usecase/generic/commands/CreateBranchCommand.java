package co.com.inventory.usecase.generic.commands;

import co.com.inventory.usecase.generic.Command;

public class CreateBranchCommand extends Command {
    private String branchName;
    private String branchLocation;

    public CreateBranchCommand() {
    }

    public CreateBranchCommand(String branchName, String branchLocation) {
        this.branchName = branchName;
        this.branchLocation = branchLocation;
    }


    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public void setBranchLocation(String branchLocation) {
        this.branchLocation = branchLocation;
    }
}
