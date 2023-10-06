package co.com.inventory.api.utils;

import co.com.inventory.api.dtos.BranchDTOResponse;
import co.com.inventory.api.dtos.ProductDTOResponse;
import co.com.inventory.model.branch.Branch;
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

    public static List<BranchDTOResponse> listBranchToListBranchDTO(List<Branch> branchList) {
        List<BranchDTOResponse> branchDTOResponses = new ArrayList<>();
        branchList.forEach(branch -> {
            BranchDTOResponse branchDTOResponse = new BranchDTOResponse();
            branchDTOResponse.setBranchId(branch.getBranchId().getBranchId());
            branchDTOResponse.setBranchName(branch.getBranchName().getBranchName());
            branchDTOResponse.setBranchCountry(branch.getBranchLocation().getBranchCountry());
            branchDTOResponse.setBranchCity(branch.getBranchLocation().getBranchCity());


            branchDTOResponses.add(branchDTOResponse);


        });
        return branchDTOResponses;

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
