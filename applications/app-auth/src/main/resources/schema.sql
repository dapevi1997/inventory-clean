USE prueba1;

-- Create the Branch table
CREATE TABLE IF NOT EXISTS branch (
    branch_id VARCHAR(100) NOT NULL,
    branch_name VARCHAR(100),
    branch_country VARCHAR(100),
    branch_city VARCHAR(100),
    PRIMARY KEY (branch_id)
    );
    -- Create the Product table
    CREATE TABLE IF NOT EXISTS product (
        product_id VARCHAR(100) NOT NULL,
        product_name VARCHAR(100),
        product_description VARCHAR(100),
        product_price FLOAT,
        product_inventory_stock INTEGER,
        product_category VARCHAR(100),
        branch_id VARCHAR(100) NOT NULL,
        PRIMARY KEY (product_id),
        FOREIGN KEY (branch_id)
        REFERENCES branch(branch_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
        );

            -- Create the Product table
            CREATE TABLE IF NOT EXISTS user_table (
                user_id VARCHAR(100) NOT NULL,
                user_name VARCHAR(100),
                user_lastName VARCHAR(100),
                user_password VARCHAR(100),
                user_email VARCHAR(100),
                user_role VARCHAR(100),
                branch_id VARCHAR(100) NOT NULL,
                PRIMARY KEY (user_id),
                FOREIGN KEY (branch_id)
                REFERENCES branch(branch_id)
                ON DELETE CASCADE
                ON UPDATE CASCADE
                );

        -- Create the Product table
            CREATE TABLE IF NOT EXISTS product_sale (
                product_sale_id VARCHAR(100) NOT NULL,
                product_sale_price FLOAT,
                product_sale_amount INT,
                PRIMARY KEY (product_sale_id)
                );

       -- Create the Product table
                            CREATE TABLE IF NOT EXISTS sale (
                                branch_id VARCHAR(100) NOT NULL,
                                product_id VARCHAR(100) NOT NULL,
                                product_sale_id VARCHAR(100) NOT NULL,
                                PRIMARY KEY (branch_id, product_id, product_sale_id),
                                FOREIGN KEY (product_id)
                                REFERENCES product(product_id)
                                ON DELETE CASCADE
                                ON UPDATE CASCADE,
                                     FOREIGN KEY (product_sale_id)
                                       REFERENCES product_sale(product_sale_id)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE,
                                                  FOREIGN KEY (branch_id)
                                                           REFERENCES branch(branch_id)
                                                                        ON DELETE CASCADE
                                                                        ON UPDATE CASCADE

                                );

    --- create a user root

         INSERT INTO branch(branch_id,branch_name,branch_country,branch_city) values ("idBranchRoot","Root","Root","Root")
                                ON DUPLICATE KEY UPDATE
                                  branch_name = VALUES(branch_name),
                                  branch_country = VALUES(branch_country),
                                  branch_city = VALUES(branch_city);



             INSERT INTO user_table (user_id, user_name, user_lastName, user_password, user_email, user_role, branch_id)
                                VALUES ('idRoot', 'Root', 'Root', '$2a$10$LWBz8.V9PiPZjJWxN49vO.E/igf80rs9URLjuRPZPZy.dqUztX9We',
                                'root@root.com', 'ROLE_SUPERADMIN', 'idBranchRoot')
                                ON DUPLICATE KEY UPDATE
                                  user_name = VALUES(user_name),
                                  user_lastName = VALUES(user_lastName),
                                  user_password = VALUES(user_password),
                                  user_email = VALUES(user_email),
                                  user_role = VALUES(user_role),
                                  branch_id = VALUES(branch_id);
