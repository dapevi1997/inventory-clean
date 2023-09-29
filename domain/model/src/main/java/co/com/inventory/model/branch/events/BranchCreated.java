package co.com.inventory.model.branch.events;

import co.com.inventory.model.branch.generic.DomainEvent;

public class BranchCreated extends DomainEvent {
    private String branchName;
    private String branchCountry;
    private String branchCity;

    public BranchCreated() {
        super("co.com.inventory.model.branch.events.BranchCreated");
    }

    public BranchCreated(String branchName, String branchCountry, String branchCity) {
        super("co.com.inventory.model.branch.events.BranchCreated");
        this.branchName = branchName;
        this.branchCountry = branchCountry;
        this.branchCity = branchCity;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchCountry() {
        return branchCountry;
    }

    public String getBranchCity() {
        return branchCity;
    }
}
