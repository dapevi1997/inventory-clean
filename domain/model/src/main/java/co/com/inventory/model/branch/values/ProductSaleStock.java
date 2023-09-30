package co.com.inventory.model.branch.values;

import co.com.inventory.model.branch.generic.ValueObject;

import java.util.Objects;

public class ProductSaleStock implements ValueObject<Integer> {
    private Integer productSaleStock;

    public ProductSaleStock(String productSaleStock) {
        Objects.requireNonNull(productSaleStock, "El campo productSaleStock no puede ser nulo");
        try {
            this.productSaleStock = Integer.parseInt(productSaleStock);
        }catch (Exception e){
            throw new NumberFormatException("El campo productPrice debe ser un número entero");
        }

    }

    @Override
    public Integer value() {
        return productSaleStock;
    }

    public Integer getProductSaleStock() {
        return productSaleStock;
    }

    @Override
    public String toString() {
        return  productSaleStock.toString() ;

    }
}
