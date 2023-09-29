package co.com.inventory.mysql.config.utilities;

import co.com.inventory.model.branch.Branch;
import co.com.inventory.mysql.config.dto.BranchDTO;
import co.com.inventory.mysql.config.dto.ProductDTO;
import co.com.inventory.mysql.config.dto.UserDTO;
import co.com.inventory.mysql.config.models.BranchMySQL;
import co.com.inventory.mysql.config.models.Product;
import co.com.inventory.mysql.config.models.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MapperUtils {
/*    public Function<Branch, BranchMySQL> mapperBranchToBranchMySql(){
        return branch-> {
            BranchMySQL branchMySQL = new BranchMySQL();
            branchMySQL.setBranchName(branch.getBranchName().getBranchName());
            branchMySQL.setBranchLocation(branch.getBranchLocation().getBranchLocation());

            return branchMySQL;

        };
    }*/


    public Function<ProductDTO, Product> mapperToProduct(){
        return productDTO -> {
            Product product = new Product();
            product.setProductName(productDTO.getProductName());
            product.setProductDescription(productDTO.getProductDescription());
            product.setProductPrice(productDTO.getProductPrice());
            product.setProductInventoryStock(productDTO.getProductInventoryStock());
            product.setProductCategory(productDTO.getProductCategory());

            return product;

        };
    }

    public Function<UserDTO, User> mapperToUser(){
        return userDTO -> {
            User user = new User();
            user.setUserName(userDTO.getUserName());
            user.setUserLastName(userDTO.getUserLastName());
            user.setUserPassword(userDTO.getUserPassword());
            user.setUserEmail(userDTO.getUserEmail());
            user.setUserRole(userDTO.getUserRole());
            return user;

        };
    }
}
