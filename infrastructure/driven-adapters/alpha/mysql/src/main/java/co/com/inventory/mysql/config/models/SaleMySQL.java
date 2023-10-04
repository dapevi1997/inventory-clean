package co.com.inventory.mysql.config.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("prueba1.sale")
public class SaleMySQL {
    @Column("branch_id")
    private String branchId;
    @Column("product_id")
    private String productId;
    @Column("product_sale_id")
    private String productSaleId;
}
