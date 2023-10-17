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
                user_email VARCHAR(100) UNIQUE,
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
                                                sale_type VARCHAR(50),
                                                                sale_date VARCHAR(50),
                                                                sale_user VARCHAR(50),
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