package co.com.inventory.model.branch.values;


import co.com.inventory.model.branch.exceptions.BlankStringException;

import java.util.Objects;

public class BranchId{
    private String branchId;
    public BranchId(String branchId) {
        Objects.requireNonNull(branchId, "El campo branchId no puede ser nulo");
        if(branchId.isBlank()){
            throw new BlankStringException("El campo branchId no puede estar vacío");
        }
            this.branchId = branchId;

    }
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
    public String getBranchId() {
        return branchId;
    }

    public static BranchId of(String uuid){
        return new BranchId(uuid);
    }
}
