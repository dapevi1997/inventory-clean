package co.com.inventory.mysql.config.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("prueba.user_table")
public class User {
    @Id
    @Column("id")
    private Long id;
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
}
