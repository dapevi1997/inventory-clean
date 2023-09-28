package co.com.inventory.model.branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.events.BranchCreated;
import co.com.inventory.model.branch.events.ProductAdded;
import co.com.inventory.model.branch.events.UserRegistered;
import co.com.inventory.model.branch.generic.AggregateRoot;
import co.com.inventory.model.branch.generic.DomainEvent;
import co.com.inventory.model.branch.values.*;

import java.util.List;
import java.util.Set;

public class Branch extends AggregateRoot<BranchId> {
    protected BranchName branchName;
    protected BranchLocation branchLocation;
    protected Set<Product> products;
    protected Set<User> users;

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
        return branch;
    }
    //TODO: implementar métodos para los casos de uso
    public void addProduct(ProductId productId, ProductName productName, ProductDescription productDescription,
    ProductPrice productPrice, ProductInventoryStock productInventoryStock, ProductCategory productCategory){
        subscribe( new BranchChange(this));
        appendChange(new ProductAdded(productId.value(),productName.value(),productDescription.value(),
                productPrice.value(),productInventoryStock.value(),productCategory.value()));
    }

    public void registerUser(UserId userId,UserName userName, UserPassword userPassword, UserEmail userEmail, UserRole userRole){
        subscribe(new BranchChange(this));
        appendChange(new UserRegistered(userId.value(),userName.value(),userPassword.value(),userEmail.value(),userRole.value()));
    }


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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
