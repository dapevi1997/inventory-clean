package co.com.inventory.usecase.utils;

import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.values.ProductSaleId;
import co.com.inventory.model.branch.values.ProductSalePrice;
import co.com.inventory.model.branch.values.ProductSaleStock;
import co.com.inventory.usecase.generic.commands.AddProductSaleCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapperUtils {
    public static Function<List<ProductSaleUtil>, List<ProductSale>> mapperListProductSaleUtilToListProductSale() {
        return productSaleUtilList -> {
            List<ProductSale> productSales = new ArrayList<>();
            productSaleUtilList.forEach(productSaleUtil -> {
                productSales.add(new ProductSale(ProductSaleId.of(productSaleUtil.getProductSaleId()), new ProductSalePrice(Float.parseFloat(productSaleUtil.getProductSalePrice())),
                       new ProductSaleStock( Integer.parseInt(productSaleUtil.getProductSaleStock()))));
            });
            return productSales;
        };
    }

}
