package co.com.inventory.model.branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.values.*;

import java.util.List;
import java.util.Set;

public class Branch {

    protected BranchId branchId;
    protected BranchName branchName;
    protected BranchLocation branchLocation;
    protected List<Product> products;
    protected Set<User> users;
    protected List<ProductSale> productSales;

    public Branch(BranchId branchId, BranchName branchName, BranchLocation branchLocation) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.branchLocation = branchLocation;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<ProductSale> getProductSales() {
        return productSales;
    }

    public void setProductSales(List<ProductSale> productSales) {
        this.productSales = productSales;
    }
}
