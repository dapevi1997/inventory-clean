package co.com.inventory.mysql.config.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("prueba1.product_sale")
public class ProductSaleMySQL {
    @Id
    @Column("product_sale_id")
    private String productSaleId;
    @Column("product_sale_price")
    private Float productSalePrice;
    @Column("product_sale_amount")
    private Integer productSaleAmount;

}
