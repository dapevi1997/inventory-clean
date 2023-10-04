package co.com.inventory.mysql.config.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("prueba1.sale")
public class SaleMySQL {
    @Id
    @Column("id")
    private Long id;
    @Column("product_id")
    private Long productId;
    @Column("product_sale_id")
    private Long productSaleId;
    @Column("sale_uuid")
    private String saleUuid;
}
