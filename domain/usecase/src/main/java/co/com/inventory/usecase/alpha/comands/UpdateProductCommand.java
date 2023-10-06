package co.com.inventory.usecase.alpha.comands;

public class UpdateProductCommand {
    private String idProduct;
    private Integer productInventoryStock;

    public UpdateProductCommand() {
    }

    public UpdateProductCommand(String idProduct, Integer productInventoryStock) {

        this.idProduct = idProduct;
        this.productInventoryStock = productInventoryStock;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getProductInventoryStock() {
        return productInventoryStock;
    }

    public void setProductInventoryStock(Integer productInventoryStock) {
        this.productInventoryStock = productInventoryStock;
    }
}
