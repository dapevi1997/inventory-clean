package co.com.inventory.model.branch.values;


import co.com.inventory.model.branch.exceptions.BlankStringException;


import java.util.Objects;

public class BranchName {
    private String branchName;

    public BranchName() {
    }

    public BranchName(String branchName) {
        Objects.requireNonNull(branchName,"El campo branchName no puede ser nulo");

        if(branchName.isBlank()){
            throw new BlankStringException("El campo branchName no puede estar vacío");
        }

        this.branchName = branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchName() {
        return branchName;
    }
}
