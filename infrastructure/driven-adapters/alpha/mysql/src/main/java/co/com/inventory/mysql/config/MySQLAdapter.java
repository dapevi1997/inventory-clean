package co.com.inventory.mysql.config;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.ProductSale;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.mysql.config.models.*;
import co.com.inventory.mysql.config.repositories.*;
import co.com.inventory.usecase.generic.gateways.MySqlRepository;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Repository
public class MySQLAdapter implements MySqlRepository {
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final WebClient webClient;
    private final ProductSaleRepository productSaleRepository;
    private final SaleRepository saleRepository;


    public MySQLAdapter(R2dbcEntityTemplate r2dbcEntityTemplate,
                        WebClient webClient, ProductSaleRepository productSaleRepository, SaleRepository saleRepository
                        ) {
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
        this.webClient = webClient;
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
        userMySQL.setUserId(UUID.randomUUID().toString());
        userMySQL.setUserName(userName);
        userMySQL.setUserLastName(userLastName);
        userMySQL.setUserPassword(userPassword);
        userMySQL.setUserEmail(userEmail);
        userMySQL.setUserRole(userRol);
        userMySQL.setBranchId(branchId);

        return r2dbcEntityTemplate.insert(userMySQL).map(userMySQL1 -> {
            return new User(UserId.of(userMySQL1.getUserId()),
                    new UserName(userMySQL1.getUserName()),
                    new UserlastName(userMySQL1.getUserLastName()),
                    new UserPassword(userMySQL1.getUserPassword()),
                    new UserEmail(userMySQL1.getUserEmail()),
                    new UserRole(userMySQL1.getUserRole()));
        }
        );
    }

    @Override
    public Flux<ProductSale> saveSale(String branchId, List<ProductSale> productSaleList, Float discount) {

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
                    .bodyToMono(Float.class)
                    .flatMap(
                    productPrice -> {
                        productSaleMySQL.setProductSalePrice(productPrice*discount);
                       return r2dbcEntityTemplate.insert(productSaleMySQL);
                    }
                    ).flatMap(productSaleMySQL1 -> {
                        SaleMySQL saleMySQL = new SaleMySQL();
                        saleMySQL.setBranchId(branchId);
                        saleMySQL.setProductSaleId(productSaleMySQL1.getProductSaleId());
                        saleMySQL.setProductId(idProduct);

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

    /*        return r2dbcEntityTemplate.insert(productSaleMySQL).map(

                    productSaleMySQL1 -> {
                        ProductSale productSale1 = new ProductSale(
                                ProductSaleId.of(productSaleMySQL1.getProductSaleId()),
                                new ProductSalePrice(productSaleMySQL1.getProductSalePrice().toString()),
                                new ProductSaleStock(productSaleMySQL1.getProductSaleAmount().toString())
                        );
                        return productSale1;
                    }
            );*/
        });

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
