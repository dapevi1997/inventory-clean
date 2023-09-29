package co.com.inventory.mysql.config.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
public class Product {
    @Id
    @Column("id")
    private Long id;
    @Column("product_name")
    private String productName;
    @Column("product_description")
    private String productDescription;
    @Column("product_price")
    private Float productPrice;
    @Column("product_inventory_stock")
    private Integer productInventoryStock;
    @Column("product_category")
    private String productCategory;
}
