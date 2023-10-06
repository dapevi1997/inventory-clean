package co.com.inventory.api.utils;

import co.com.inventory.api.dtos.ProductDTOResponse;
import co.com.inventory.model.branch.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MapperMysqlQuery {
    public static List<ProductDTOResponse> listProdutToListProductDTO(List<Product> productList) {
        List<ProductDTOResponse> productDTOResponses = new ArrayList<>();
        productList.forEach(product -> {
            ProductDTOResponse productDTOResponse = new ProductDTOResponse();
            productDTOResponse.setProductId(product.getProductId().getProductId());
            productDTOResponse.setProductDescription(product.getProductDescription().getProductDescription());
            productDTOResponse.setProductName(product.getProductName().getProductName());
            productDTOResponse.setProductCategory(product.getProductCategory().getProductCategory());
            productDTOResponse.setProductInventoryStock(product.getProductInventoryStock().getProductInventoryStock());
            productDTOResponse.setProductPrice(product.getProductPrice().getProductPrice());

            productDTOResponses.add(productDTOResponse);


        });
        return productDTOResponses;

    }

    public static ProductDTOResponse ProdutToProductDTO(Product product) {
        ProductDTOResponse productDTOResponse = new ProductDTOResponse();


        productDTOResponse.setProductId(product.getProductId().getProductId());
        productDTOResponse.setProductDescription(product.getProductDescription().getProductDescription());
        productDTOResponse.setProductName(product.getProductName().getProductName());
        productDTOResponse.setProductCategory(product.getProductCategory().getProductCategory());
        productDTOResponse.setProductInventoryStock(product.getProductInventoryStock().getProductInventoryStock());
        productDTOResponse.setProductPrice(product.getProductPrice().getProductPrice());


        return productDTOResponse;

    }
}
