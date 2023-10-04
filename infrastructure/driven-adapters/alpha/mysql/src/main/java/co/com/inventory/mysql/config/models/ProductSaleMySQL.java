package co.com.inventory.mysql.config.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("prueba1.product_sale")
public class ProductSaleMySQL {
    @Id
    @Column("id")
    private Long id;
    @Column("product_sale_price")
    private Float productSalePrice;
    @Column("product_sale_amount")
    private Integer productSaleAmount;
    @Column("product_id")
    private Long productId;
    @Column("branch_id")
    private Long branchId;

}
