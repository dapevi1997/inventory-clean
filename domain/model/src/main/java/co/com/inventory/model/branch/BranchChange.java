package co.com.inventory.model.branch;

import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.model.branch.generic.EventChange;
import co.com.inventory.model.branch.values.BranchLocation;
import co.com.inventory.model.branch.values.BranchName;

public class BranchChange extends EventChange {
    public BranchChange(Branch branch) {
        apply(
                (BranchCreated event) -> {
                    //TODO: terminar de implementar
                    branch.branchName = new BranchName(event.getBranchName());
                    branch.branchLocation = new BranchLocation(event.getBranchLocation());
                }
        );
    }
}
