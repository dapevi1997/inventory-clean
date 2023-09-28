package co.com.inventory.model.branch;
import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.model.branch.generic.AggregateRoot;
import co.com.inventory.model.branch.generic.DomainEvent;
import co.com.inventory.model.branch.values.BranchId;
import co.com.inventory.model.branch.values.BranchLocation;
import co.com.inventory.model.branch.values.BranchName;

import java.util.List;

public class Branch extends AggregateRoot<BranchId> {
    protected BranchName branchName;
    protected BranchLocation branchLocation;

    public Branch(BranchId branchId, BranchName branchName, BranchLocation branchLocation) {
        super(branchId);
        this.branchName = branchName;
        this.branchLocation = branchLocation;
        subscribe(new BranchChange(this));
        appendChange(new BranchCreated(branchName.value(),branchLocation.value()));
    }

    public Branch(BranchId id) {
        super(id);
        //TODO: suscribirse a los eventos del agregado ej: subscribe(new PatientChange(this));
        subscribe(new BranchChange(this));
    }
    //TODO: implementar la factoría
    public static Branch from(BranchId branchId, List<DomainEvent> events){
        Branch branch = new Branch(branchId);
        events.forEach(domainEvent -> branch.applyEvent(domainEvent));
        return null;
    }
    //TODO: implementar métodos para los casos de uso


    public BranchName getBranchName() {
        return branchName;
    }

    public void setBranchName(BranchName branchName) {
        this.branchName = branchName;
    }

    public BranchLocation getBranchLocation() {
        return branchLocation;
    }

    public void setBranchLocation(BranchLocation branchLocation) {
        this.branchLocation = branchLocation;
    }
}
