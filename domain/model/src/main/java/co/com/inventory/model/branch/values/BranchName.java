package co.com.inventory.model.branch.values;


import co.com.inventory.model.branch.generic.ValueObject;

import java.util.Objects;

public class BranchName implements ValueObject<String> {
    private String branchName;

    public BranchName(String branchName) {
        try {
            Objects.requireNonNull(branchName);
        }catch (NullPointerException e){
            throw new NullPointerException("El campo branchName no puede ser nulo");
        }

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
