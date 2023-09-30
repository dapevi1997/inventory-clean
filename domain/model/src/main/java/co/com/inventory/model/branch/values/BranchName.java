package co.com.inventory.model.branch.values;


import co.com.inventory.model.branch.exceptions.BlankStringException;
import co.com.inventory.model.branch.generic.ValueObject;


import java.util.Objects;

public class BranchName implements ValueObject<String> {
    private String branchName;

    public BranchName(String branchName) {
        Objects.requireNonNull(branchName,"El campo branchName no puede ser nulo");

        if(branchName.isBlank()){
            throw new BlankStringException("El campo branchName no puede estar vacío");
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
