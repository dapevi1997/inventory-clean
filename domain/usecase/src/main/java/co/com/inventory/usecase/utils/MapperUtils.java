package co.com.inventory.usecase.utils;

import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.utils.ProductSaleUtil;
import co.com.inventory.model.branch.values.ProductSaleId;
import co.com.inventory.model.branch.values.ProductSaleStock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MapperUtils {
    public static Function<List<ProductSaleUtil>, List<ProductSale>> mapperListProductSaleUtilToListProductSale() {
        return productSaleUtilList -> {
            List<ProductSale> productSales = new ArrayList<>();
            productSaleUtilList.forEach(productSaleUtil -> {
                productSales.add(new ProductSale(ProductSaleId.of(productSaleUtil.getProductSaleId()),
                       null,
                       new ProductSaleStock( productSaleUtil.getProductSaleStock())));
            });
            return productSales;
        };
    }

}
