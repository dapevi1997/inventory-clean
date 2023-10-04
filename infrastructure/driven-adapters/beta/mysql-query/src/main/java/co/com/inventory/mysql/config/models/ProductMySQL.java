package co.com.inventory.mysql.config.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("prueba1.product")
public class ProductMySQL {
    @Id
    @Column("product_id")
    private String productId;
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
    @Column("branch_id")
    private String branchId;
}
