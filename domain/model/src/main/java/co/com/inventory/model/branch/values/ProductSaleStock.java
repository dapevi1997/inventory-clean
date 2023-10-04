package co.com.inventory.model.branch.values;


import java.util.Objects;

public class ProductSaleStock {
    private Integer productSaleStock;

    public ProductSaleStock(String productSaleStock) {
        Objects.requireNonNull(productSaleStock, "El campo productSaleStock no puede ser nulo");
        try {
            this.productSaleStock = Integer.parseInt(productSaleStock);
        }catch (Exception e){
            throw new NumberFormatException("El campo productPrice debe ser un número entero");
        }

    }

    public void setProductSaleStock(Integer productSaleStock) {
        this.productSaleStock = productSaleStock;
    }

    public Integer getProductSaleStock() {
        return productSaleStock;
    }


}
