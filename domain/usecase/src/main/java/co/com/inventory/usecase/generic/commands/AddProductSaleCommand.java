package co.com.inventory.usecase.generic.commands;

import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.usecase.generic.Command;
import co.com.inventory.usecase.utils.ProductSaleUtil;

import java.util.List;

public class AddProductSaleCommand extends Command {
    private String branchId;
    private String productSaleId;
    private List<ProductSaleUtil> productSalesUtil;

    public AddProductSaleCommand() {
    }

    public AddProductSaleCommand(String branchId, String productSaleId, List<ProductSaleUtil> productSalesUtil) {
        this.branchId = branchId;
        this.productSaleId = productSaleId;
        this.productSalesUtil = productSalesUtil;
    }

    public List<ProductSaleUtil> getProductSalesUtil() {
        return productSalesUtil;
    }

    public String getProductSaleId() {
        return productSaleId;
    }

    public String getBranchId() {
        return branchId;
    }
}
