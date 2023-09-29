package co.com.inventory.mysql.config.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("prueba1.branch")
public class BranchMySQL {
    @Id
    @Column("id")
    private Long id;
    @Column("branch_name")
    private String branchName;
    @Column("branch_country")
    private String branch_country;
    @Column("branch_city")
    private String branchCity;
}
