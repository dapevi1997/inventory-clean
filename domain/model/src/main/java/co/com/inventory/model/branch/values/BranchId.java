package co.com.inventory.model.branch.values;


import co.com.inventory.model.branch.generic.Identity;

public class BranchId extends Identity {
    public BranchId() {
    }
    public BranchId(String uuid) {
        super(uuid);
    }
    public static BranchId of(String uuid){
        return new BranchId(uuid);
    }
}
