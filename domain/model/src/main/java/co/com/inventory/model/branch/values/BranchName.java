package co.com.inventory.model.branch.values;


import co.com.inventory.model.branch.generic.ValueObject;

public class BranchName implements ValueObject<String> {
    private String branchName;

    public BranchName(String branchName) {
        this.branchName = branchName;
    }

    @Override
    public String value() {
        return branchName;
    }

    public String getBranchName() {
        return branchName;
    }
}
