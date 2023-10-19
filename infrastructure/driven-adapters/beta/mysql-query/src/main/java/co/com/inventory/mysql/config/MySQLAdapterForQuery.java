package co.com.inventory.mysql.config;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.utils.SalesByBranchDTOModel;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.mysql.config.dtos.ProductDTOResponse;
import co.com.inventory.mysql.config.dtos.SalesByBranchDTO;
import co.com.inventory.mysql.config.models.*;
import co.com.inventory.mysql.config.repositories.BranchRepository;
import co.com.inventory.mysql.config.repositories.ProductRepository;
import co.com.inventory.mysql.config.repositories.UserRepository;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class MySQLAdapterForQuery implements MySqlRepositoryQuery {
    private final ProductRepository productRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final BranchRepository branchRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MySQLAdapterForQuery(ProductRepository productRepository, R2dbcEntityTemplate r2dbcEntityTemplate, BranchRepository branchRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.productRepository = productRepository;
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
        this.branchRepository = branchRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<Product> findProductbyId(String productId) {
        return productRepository.findById(productId)
                .map(productMySQL -> {
                   // return productMySQL.getProductPrice();
                    return new Product(BranchId.of(productMySQL.getBranchId()), ProductId.of(productMySQL.getProductId().toString()),
                            new ProductName(productMySQL.getProductName()),
                            new ProductDescription(productMySQL.getProductDescription()),
                            new ProductPrice(productMySQL.getProductPrice().toString()),
                            new ProductInventoryStock(productMySQL.getProductInventoryStock().toString()),
                            new ProductCategory(productMySQL.getProductCategory()));
                });
    }

    @Override
    public Flux<Product> getAllProducts() {
        return productRepository.findAll().map(
                productMySQL -> {
                    Product product = new Product(BranchId.of(productMySQL.getBranchId()), ProductId.of(productMySQL.getProductId().toString()),
                            new ProductName(productMySQL.getProductName()),
                            new ProductDescription(productMySQL.getProductDescription()),
                            new ProductPrice(productMySQL.getProductPrice().toString()),
                            new ProductInventoryStock(productMySQL.getProductInventoryStock().toString()),
                            new ProductCategory(productMySQL.getProductCategory()));
                    return product;
                }
        );
    }

    @Override
    public Flux<Branch> getAllBranchs() {
        return branchRepository.findAll().map(
                branchMySQL -> {
                    Branch branch = new Branch(BranchId.of(branchMySQL.getBranch_id()),
                            new BranchName(branchMySQL.getBranchName()),
                            new BranchLocation(branchMySQL.getBranch_country(), branchMySQL.getBranchCity()));

                    return branch;
                }
        );
    }
   @Override
    public Flux<SalesByBranchDTOModel> getSalesByBranchId(String branchId){
        String sql = "SELECT b.branch_name, p.product_name, p.product_price, ps.product_sale_price, ps.product_sale_amount, s.sale_type, s.sale_date, s.sale_user " +
                "FROM branch b " +
                "INNER JOIN product p ON b.branch_id = p.branch_id " +
                "INNER JOIN sale s ON p.product_id = s.product_id " +
                "INNER JOIN product_sale ps ON s.product_sale_id = ps.product_sale_id " +
                "WHERE b.branch_id = :branchId";

        return r2dbcEntityTemplate.getDatabaseClient()
                .sql(sql)
                .bind("branchId", branchId)
                .map((row, metadata) -> {
                    String branchName = row.get("branch_name", String.class);
                    String productName = row.get("product_name", String.class);
                    Float productPrice = row.get("product_price", Float.class);
                    Float productSalePrice = row.get("product_sale_price", Float.class);
                    Integer productSaleAmount = row.get("product_sale_amount", Integer.class);
                    String saleDate = row.get("sale_date", String.class);
                    String saleType = row.get("sale_type", String.class);
                    String saleUser = row.get("sale_user", String.class);

                    SalesByBranchDTO salesByBranchDTO = new SalesByBranchDTO();
                    salesByBranchDTO.setBranchName(branchName);
                    salesByBranchDTO.setProductName(productName);
                    salesByBranchDTO.setProductPrice(productPrice);
                    salesByBranchDTO.setProductSalePrice(productSalePrice);
                    salesByBranchDTO.setProductSaleAmount(productSaleAmount);
                    salesByBranchDTO.setSaleDate(saleDate);
                    salesByBranchDTO.setSaleType(saleType);
                    salesByBranchDTO.setSaleUser(saleUser);

                    SalesByBranchDTOModel salesByBranchDTOModel = new SalesByBranchDTOModel();
                    salesByBranchDTOModel.setBranchName(branchName);
                    salesByBranchDTOModel.setProductName(productName);
                    salesByBranchDTOModel.setProductPrice(productPrice);
                    salesByBranchDTOModel.setProductSalePrice(productSalePrice);
                    salesByBranchDTOModel.setProductSaleAmount(productSaleAmount);
                    salesByBranchDTOModel.setSaleDate(saleDate);
                    salesByBranchDTOModel.setSaleType(saleType);
                    salesByBranchDTOModel.setSaleUser(saleUser);

                    return salesByBranchDTOModel;
                })
                .all();
    }

    @Override
    public Mono<User> findUserByEmail(String email) {
        return userRepository.findByUserEmail(email).map(
                userMySQL -> {
                    User user = new User(
                            BranchId.of(userMySQL.getBranchId()),
                            UserId.of(userMySQL.getUserId()),
                            new UserName(userMySQL.getUserName()),
                            new UserlastName(userMySQL.getUserLastName()),
                            new UserPassword(userMySQL.getUserPassword()),
                            new UserEmail(userMySQL.getUserEmail()),
                            new UserRole(userMySQL.getUserRole())
                    );
                    return user;
                }
        );
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
            return new Product(BranchId.of(productMySQL1.getBranchId()), ProductId.of(productMySQL1.getProductId()),
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
        userMySQL.setUserId(UUID.randomUUID().toString());
        userMySQL.setUserName(userName);
        userMySQL.setUserLastName(userLastName);

        userMySQL.setUserPassword(passwordEncoder.encode(userPassword));

        userMySQL.setUserEmail(userEmail);
        userMySQL.setUserRole(userRol);
        userMySQL.setBranchId(branchId);

        return r2dbcEntityTemplate.insert(userMySQL).map(userMySQL1 -> {
                    return new User(BranchId.of(userMySQL1.getBranchId()), UserId.of(userMySQL1.getUserId()),
                            new UserName(userMySQL1.getUserName()),
                            new UserlastName(userMySQL1.getUserLastName()),
                            new UserPassword(userMySQL1.getUserPassword()),
                            new UserEmail(userMySQL1.getUserEmail()),
                            new UserRole(userMySQL1.getUserRole()));
                }
        );
    }

    @Override
    public Flux<ProductSale> saveSale(String branchId, List<ProductSale> productSaleList, Float discount, String type, String user) {

        return Flux.fromIterable(productSaleList)
                .flatMap(productSale -> {
                    ProductSaleMySQL productSaleMySQL = new ProductSaleMySQL();
                    productSaleMySQL.setProductSaleId(UUID.randomUUID().toString());
                    productSaleMySQL.setProductSaleAmount(productSale.getProductSaleStock().getProductSaleStock());
                    productSaleMySQL.setProductSalePrice(productSale.getProductSalePrice().getProductSalePrice());


                    String idProduct = productSale.getProductSaleId().getProductSaleId();

                    return findProductbyId(idProduct)
                            .map(product -> {
                                ProductDTOResponse productDTOResponse = new ProductDTOResponse();
                                productDTOResponse.setProductId(product.getProductId().getProductId());
                                productDTOResponse.setProductName(product.getProductName().getProductName());
                                productDTOResponse.setProductDescription(product.getProductDescription().getProductDescription());
                                productDTOResponse.setProductPrice(product.getProductPrice().getProductPrice());
                                productDTOResponse.setProductInventoryStock(product.getProductInventoryStock().getProductInventoryStock());
                                productDTOResponse.setProductCategory(product.getProductCategory().getProductCategory());


                                return productDTOResponse;
                            })
                            .flatMap(
                                    product -> {
                                        productSaleMySQL.setProductSalePrice(product.getProductPrice()*discount);
                                        return updateProductStock(idProduct,product.getProductInventoryStock() - productSale.getProductSaleStock().getProductSaleStock())
                                                .then(r2dbcEntityTemplate.insert(productSaleMySQL))
                                                .flatMap(productSaleMySQL1 -> {
                                                    SaleMySQL saleMySQL = new SaleMySQL();
                                                    saleMySQL.setBranchId(branchId);
                                                    saleMySQL.setProductSaleId(productSaleMySQL1.getProductSaleId());
                                                    saleMySQL.setProductId(idProduct);
                                                    saleMySQL.setType(type);
                                                    saleMySQL.setUser(user);

                                                    saleMySQL.setDate(new Date().toString());

                                                    return r2dbcEntityTemplate.insert(saleMySQL).map(
                                                            saleMySQL1 -> {
                                                                return new ProductSale(
                                                                        ProductSaleId.of(saleMySQL1.getProductSaleId()),
                                                                        new ProductSalePrice(productSaleMySQL1.getProductSalePrice().toString()),
                                                                        new ProductSaleStock(productSaleMySQL1.getProductSaleAmount().toString())
                                                                );
                                                            }
                                                    );
                                                });
                                    }
                            );


                });


    }

    @Override
    public Mono<Product> updateProductStock(String idProduct, Integer productInventoryStock) {

        return productRepository.findById(idProduct).flatMap(
                productMySQL -> {
                    productMySQL.setProductInventoryStock(productInventoryStock);

                    return r2dbcEntityTemplate.update(productMySQL);
                }
        ).map(productMySQL -> {
            Product product = new Product(BranchId.of(productMySQL.getBranchId()), ProductId.of(productMySQL.getProductId()),
                    new ProductName(productMySQL.getProductName()),
                    new ProductDescription(productMySQL.getProductDescription()),
                    new ProductPrice(productMySQL.getProductPrice().toString()),
                    new ProductInventoryStock(productMySQL.getProductInventoryStock().toString()),
                    new ProductCategory(productMySQL.getProductCategory()));
            return product;
        });
    }

    @Override
    public Mono<Product> updateProductBranch(String idProduct, String branchId) {
        return productRepository.findById(idProduct).flatMap(
                productMySQL -> {
                    productMySQL.setBranchId(branchId);

                    return r2dbcEntityTemplate.update(productMySQL);
                }
        ).map(productMySQL -> {
            Product product = new Product(BranchId.of(productMySQL.getBranchId()), ProductId.of(productMySQL.getProductId()),
                    new ProductName(productMySQL.getProductName()),
                    new ProductDescription(productMySQL.getProductDescription()),
                    new ProductPrice(productMySQL.getProductPrice().toString()),
                    new ProductInventoryStock(productMySQL.getProductInventoryStock().toString()),
                    new ProductCategory(productMySQL.getProductCategory()));
            return product;
        });
    }

    @Override
    public Mono<Branch> saveBranch(String branchId, String branchNameP, String branchCountry, String branchCity) {
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
}

