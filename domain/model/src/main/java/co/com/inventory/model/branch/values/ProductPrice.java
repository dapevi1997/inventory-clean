package co.com.inventory.model.branch.values;


import java.util.Objects;

public class ProductPrice {
    private Float productPrice;

    public ProductPrice() {
    }

    public ProductPrice(String productPrice) {
        Objects.requireNonNull(productPrice,"El campo productPrice no puede ser nulo");
        try {
            this.productPrice = Float.parseFloat(productPrice);
        }catch (Exception e){
            throw new NumberFormatException("El campo productPrice debe ser un número");
        }

    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Float getProductPrice() {
        return productPrice;
    }
}
