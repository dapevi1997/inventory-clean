package co.com.inventory.mysql.config.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("prueba1.user_table")
public class UserMySQL {
    @Id
    @Column("user_id")
    private String userId;
    @Column("user_name")
    private String userName;
    @Column("user_lastName")
    private String userLastName;
    @Column("user_password")
    private String userPassword;
    @Column("user_email")
    private String userEmail;
    @Column("user_role")
    private String userRole;
    @Column("branch_id")
    private String branchId;
}
