package co.com.inventory.mysql.config;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.mysql.config.dtos.ProductDTOResponse;
import co.com.inventory.mysql.config.models.*;
import co.com.inventory.mysql.config.repositories.*;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class MySQLAdapter implements MySqlRepository {
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final WebClient webClient;
    private final ProductRepository productRepository;
    private final ProductSaleRepository productSaleRepository;
    private final SaleRepository saleRepository;
    private final PasswordEncoder passwordEncoder;



    public MySQLAdapter(R2dbcEntityTemplate r2dbcEntityTemplate,
                        WebClient webClient, ProductRepository productRepository, ProductSaleRepository productSaleRepository, SaleRepository saleRepository,
                        PasswordEncoder passwordEncoder) {
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
        this.webClient = webClient;
        this.productRepository = productRepository;
        this.productSaleRepository = productSaleRepository;
        this.saleRepository = saleRepository;
        this.passwordEncoder = passwordEncoder;
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

            return webClient.get()
                    .uri("api/v1/product/" + idProduct)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(ProductDTOResponse.class)
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
}
