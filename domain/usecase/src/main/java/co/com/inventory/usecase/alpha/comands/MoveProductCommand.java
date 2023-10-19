package co.com.inventory.usecase.alpha.comands;

public class MoveProductCommand {
    private String idProduct;
    private String branchId;

    public MoveProductCommand() {
    }
    public MoveProductCommand(String idProduct, String branchId) {

        this.idProduct = idProduct;
        this.branchId = branchId;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getBranchId() {
        return branchId;
    }
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
