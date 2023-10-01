package co.com.inventory.mysql.config.utilities;

import co.com.inventory.mysql.config.dto.ProductDTO;
import co.com.inventory.mysql.config.dto.UserDTO;
import co.com.inventory.mysql.config.models.ProductMySQL;
import co.com.inventory.mysql.config.models.UserMySQL;
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


    public Function<ProductDTO, ProductMySQL> mapperToProduct(){
        return productDTO -> {
            ProductMySQL productMySQL = new ProductMySQL();
            productMySQL.setProductName(productDTO.getProductName());
            productMySQL.setProductDescription(productDTO.getProductDescription());
            productMySQL.setProductPrice(productDTO.getProductPrice());
            productMySQL.setProductInventoryStock(productDTO.getProductInventoryStock());
            productMySQL.setProductCategory(productDTO.getProductCategory());

            return productMySQL;

        };
    }

    public Function<UserDTO, UserMySQL> mapperToUser(){
        return userDTO -> {
            UserMySQL userMySQL = new UserMySQL();
            userMySQL.setUserName(userDTO.getUserName());
            userMySQL.setUserLastName(userDTO.getUserLastName());
            userMySQL.setUserPassword(userDTO.getUserPassword());
            userMySQL.setUserEmail(userDTO.getUserEmail());
            userMySQL.setUserRole(userDTO.getUserRole());
            return userMySQL;

        };
    }
}
