package co.com.inventory.usecase.generic.commands;

import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.usecase.generic.Command;

import java.util.List;

public class AddProductSaleCommand extends Command {
    private List<ProductSale> productSales;

    public AddProductSaleCommand() {
    }

    public AddProductSaleCommand(List<ProductSale> productSales) {
        this.productSales = productSales;
    }

    public List<ProductSale> getProductSales() {
        return productSales;
    }
}
