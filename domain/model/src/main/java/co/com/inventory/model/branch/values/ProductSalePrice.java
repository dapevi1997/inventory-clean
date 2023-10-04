package co.com.inventory.model.branch.values;


import java.util.Objects;

public class ProductSalePrice {
    private Float productSalePrice;

    public ProductSalePrice(String productSalePrice) {
        Objects.requireNonNull(productSalePrice, "El campo productSalePrice no puede ser nulo");
        try {
            this.productSalePrice = Float.parseFloat(productSalePrice);
        }catch (Exception e){
            throw new NumberFormatException("El campo productSalePrice debe ser un número entero");
        }


    }

    public void setProductSalePrice(Float productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public Float getProductSalePrice() {
        return productSalePrice;
    }

}
