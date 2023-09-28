package co.com.inventory.model.branch.events;

import co.com.inventory.model.branch.generic.DomainEvent;

public class BranchCreated extends DomainEvent {
    private String branchName;
    private String branchLocation;

    public BranchCreated() {
        super("co.com.inventory.model.branch.events.BranchCreated");
    }

    public BranchCreated(String branchName, String branchLocation) {
        super("co.com.inventory.model.branch.events.BranchCreated");
        this.branchName = branchName;
        this.branchLocation = branchLocation;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchLocation() {
        return branchLocation;
    }
}
