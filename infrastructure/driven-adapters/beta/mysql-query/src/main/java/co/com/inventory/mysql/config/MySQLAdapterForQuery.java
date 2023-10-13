package co.com.inventory.mysql.config;


import co.com.inventory.model.branch.Branch;
import co.com.inventory.model.branch.entities.Product;
import co.com.inventory.model.branch.entities.User;
import co.com.inventory.model.branch.utils.SalesByBranchDTOModel;
import co.com.inventory.model.branch.values.*;
import co.com.inventory.mysql.config.dtos.SalesByBranchDTO;
import co.com.inventory.mysql.config.repositories.BranchRepository;
import co.com.inventory.mysql.config.repositories.ProductRepository;
import co.com.inventory.mysql.config.repositories.UserRepository;
import co.com.inventory.usecase.generic.gateways.MySqlRepositoryQuery;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MySQLAdapterForQuery implements MySqlRepositoryQuery {
    private final ProductRepository productRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final BranchRepository branchRepository;
    private final UserRepository userRepository;

    public MySQLAdapterForQuery(ProductRepository productRepository, R2dbcEntityTemplate r2dbcEntityTemplate, BranchRepository branchRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
        this.branchRepository = branchRepository;
        this.userRepository = userRepository;
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
        String sql = "SELECT b.branch_name, p.product_name, p.product_price, ps.product_sale_price, ps.product_sale_amount " +
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

                    SalesByBranchDTO salesByBranchDTO = new SalesByBranchDTO();
                    salesByBranchDTO.setBranchName(branchName);
                    salesByBranchDTO.setProductName(productName);
                    salesByBranchDTO.setProductPrice(productPrice);
                    salesByBranchDTO.setProductSalePrice(productSalePrice);
                    salesByBranchDTO.setProductSaleAmount(productSaleAmount);

                    SalesByBranchDTOModel salesByBranchDTOModel = new SalesByBranchDTOModel();
                    salesByBranchDTOModel.setBranchName(branchName);
                    salesByBranchDTOModel.setProductName(productName);
                    salesByBranchDTOModel.setProductPrice(productPrice);
                    salesByBranchDTOModel.setProductSalePrice(productSalePrice);
                    salesByBranchDTOModel.setProductSaleAmount(productSaleAmount);

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
                            new UserName(userMySQL.getName()),
                            new UserlastName(userMySQL.getUserLastName()),
                            new UserPassword(userMySQL.getUserPassword()),
                            new UserEmail(userMySQL.getUserEmail()),
                            new UserRole(userMySQL.getUserRole())
                    );
                    return user;
                }
        );
    }
}

