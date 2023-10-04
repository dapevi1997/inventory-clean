package co.com.inventory.mysql.config;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.utils.WraperSaveProductSales;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.mysql.config.models.*;
import co.com.inventory.mysql.config.repositories.*;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import co.com.inventory.model.branch.utils.ProductSaleUtil;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class MySQLAdapter implements MySqlRepository {
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductSaleRepository productSaleRepository;
    private final SaleRepository saleRepository;

    public MySQLAdapter(R2dbcEntityTemplate r2dbcEntityTemplate,
                        ProductRepository productRepository, UserRepository userRepository,
                        ProductSaleRepository productSaleRepository, SaleRepository saleRepository
                        ) {
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;

        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productSaleRepository = productSaleRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public Mono<Branch> saveBranch(String branchId,String branchNameP, String branchCountry, String branchCity) {
        BranchMySQL branchMySQLAux = new BranchMySQL();
        BranchName branchName = new BranchName(branchNameP);
        BranchLocation branchLocation = new BranchLocation(branchCountry, branchCity);

        branchMySQLAux.setBranch_id(branchId);
        branchMySQLAux.setBranchName(branchNameP);
        branchMySQLAux.setBranch_country(branchCountry);
        branchMySQLAux.setBranchCity(branchCity);

        return r2dbcEntityTemplate.insert(branchMySQLAux).map(branchMySQL -> {
            return new Branch(BranchId.of(branchMySQL.getBranch_id().toString()),
                    branchName, branchLocation);});

    }

    @Override
    public Mono<Product> saveProduct(String branchId, String productId, String productName, String description, String productPrice, String productInventoryStock, String productCategory) {
        ProductMySQL productMySQL = new ProductMySQL();
        productMySQL.setProductId(productId);
        productMySQL.setProductName(productName);
        productMySQL.setProductDescription(description);
        productMySQL.setProductPrice(Float.parseFloat(productPrice));
        productMySQL.setProductInventoryStock(Integer.parseInt(productInventoryStock));
        productMySQL.setProductCategory(productCategory);
        productMySQL.setBranchId(branchId);


        return r2dbcEntityTemplate.insert(productMySQL).map(productMySQL1 -> {
            return new Product(ProductId.of(productMySQL1.getProductId()),
                    new ProductName(productMySQL1.getProductName()),
                    new ProductDescription(productMySQL1.getProductDescription()),
                    new ProductPrice(productMySQL1.getProductPrice().toString()),
                    new ProductInventoryStock(productMySQL1.getProductInventoryStock().toString()),
                    new ProductCategory(productMySQL1.getProductCategory()));}
        );

    }

    @Override
    public Mono<User> saveUser(String branchId, String userName, String userLastName, String userPassword, String userEmail, String userRol) {

        UserMySQL userMySQL = new UserMySQL();
        userMySQL.setBranchId(Long.parseLong(branchId));
        userMySQL.setUserName(userName);
        userMySQL.setUserLastName(userLastName);
        userMySQL.setUserPassword(userPassword);
        userMySQL.setUserEmail(userEmail);
        userMySQL.setUserRole(userRol);


        return userRepository.save(userMySQL).map(userMySQL1 -> {
            return new User(UserId.of(userMySQL1.getId().toString()),
                    new UserName(userName),
                    new UserlastName(userLastName),
                    new UserPassword(userPassword),
                    new UserEmail(userEmail),
                    new UserRole(userRol)
            );
        });
    }

    @Override
    public Mono<Product> findProductbyId(Long productId) {
        return null;
/*        return productRepository.findById(productId)
                .map(productMySQL -> {
                    return new Product(ProductId.of(productMySQL.getProductId().toString()),
                            new ProductName(productMySQL.getProductName()),
                            new ProductDescription(productMySQL.getProductDescription()),
                            new ProductPrice(productMySQL.getProductPrice().toString()),
                            new ProductInventoryStock(productMySQL.getProductInventoryStock().toString()),
                            new ProductCategory(productMySQL.getProductCategory()));
                });*/
    }

    @Override
    public Mono<WraperSaveProductSales> saveProductSales(String branchId, List<ProductSaleUtil> productSaleUtilList, String uuid, Float discount) {
        return null;

/*        return Flux.fromIterable(productSaleUtilList)
                .flatMap(productSaleUtil -> {
                    return findProductbyId(Long.parseLong(productSaleUtil.getProductSaleId()))
                            .flatMap(
                                    product -> {
                                        ProductSaleMySQL productSaleMySQL = new ProductSaleMySQL();
                                        String id = product.identity().toString().trim().replace(":","").replace(",","");

                                        productSaleMySQL.setBranchId(Long.parseLong(branchId));
                                        productSaleMySQL.setProductSalePrice(product.getProductPrice().getProductPrice());
                                        productSaleMySQL.setProductId(Long.parseLong(id));



                                        productSaleMySQL.setProductSaleAmount(Integer.parseInt(productSaleUtil.getProductSaleStock()));



                                        return productSaleRepository.save(productSaleMySQL)
                                                .flatMap(productSaleMySQL1 -> {
                                                    Float result = productSaleMySQL1.getProductSalePrice() - discount;
                                                    productSaleUtil.setProductSalePrice(result.toString());
                                                    SaleMySQL saleMySQL = new SaleMySQL();
                                                    saleMySQL.setProductSaleId(productSaleMySQL1.getId());
                                                    saleMySQL.setProductId(Long.parseLong(product.identity().value()));
                                                    saleMySQL.setSaleUuid(uuid);



                                                    return saleRepository.save(saleMySQL);
                                                });
                                    }
                            );
                })
                .collectList()
                .map(
                        saleMySQL -> {
                            return new WraperSaveProductSales(productSaleUtilList,uuid,Long.parseLong(branchId));

                        }
                );*/

    }
}
