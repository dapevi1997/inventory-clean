package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

public class BranchLocation implements ValueObject<String> {
    private String branchLocation;

    public BranchLocation(String branchLocation) {
        this.branchLocation = branchLocation;
    }

    @Override
    public String value() {
        return branchLocation;
    }

    public String getBranchLocation() {
        return branchLocation;
    }
}
