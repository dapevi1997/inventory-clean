package co.com.inventory.model.branch.values;


import java.util.Objects;

public class ProductInventoryStock {
    private Integer productInventoryStock;

    public ProductInventoryStock(String productInventoryStock) {
        Objects.requireNonNull(productInventoryStock, "El campo productInventoryStock no puede ser nulo");
        try {
            this.productInventoryStock = Integer.parseInt(productInventoryStock);
        }catch (Exception e){
            throw new NumberFormatException("El campo productInventoryStock debe ser un número entero");
        }

    }

    public void setProductInventoryStock(Integer productInventoryStock) {
        this.productInventoryStock = productInventoryStock;
    }

    public Integer getProductInventoryStock() {
        return productInventoryStock;
    }
}
