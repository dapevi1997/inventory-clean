package co.com.inventory.model.branch.utils;

import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.values.ProductSaleId;
import co.com.inventory.model.branch.values.ProductSalePrice;
import co.com.inventory.model.branch.values.ProductSaleStock;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mapper {
    public static List<ProductSale> parseJsonToList(String jsonString) {
        List<ProductSale> listaDeObjetos = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\{[^\\}]*\\}");
        Matcher matcher = pattern.matcher(jsonString);

        while (matcher.find()) {
            String objetoStr = matcher.group().replaceAll("[{}]", "");

            String[] partes = objetoStr.split(",");

            String productSaleId = null;
            String productSaleStock = null;
            String productSalePrice = null;

            for (String parte : partes) {
                String[] keyValue = parte.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    if (key.equals("productSaleId")) {
                        productSaleId = value.replaceAll("\"", "");
                    } else if(key.equals("productSalePrice")){
                        productSalePrice = value;
                    }  else if(key.equals("productSaleStock")){
                        productSaleStock = value;
                    }
                }
            }

            if (productSaleId != null  && productSalePrice != null && productSaleStock!=null) {
                ProductSale productSale = new ProductSale(ProductSaleId.of(productSaleId),
                        new ProductSalePrice(Float.parseFloat(productSalePrice)),
                        new ProductSaleStock(Integer.parseInt(productSaleStock)));
                listaDeObjetos.add(productSale);
            }
        }

        return listaDeObjetos;
    }
}
