USE prueba1;

-- Create the Branch table
CREATE TABLE IF NOT EXISTS branch (
    id INT NOT NULL AUTO_INCREMENT,
    branch_name VARCHAR(100),
    branch_country VARCHAR(100),
    branch_city VARCHAR(100),
    PRIMARY KEY (id)
    );

    -- Create the Product table
    CREATE TABLE IF NOT EXISTS product (
        id INT NOT NULL AUTO_INCREMENT,
        product_name VARCHAR(100),
        product_description VARCHAR(100),
        product_price FLOAT,
        product_inventory_stock INTEGER,
        product_category VARCHAR(100),
        branch_id INT,
        PRIMARY KEY (id),
        FOREIGN KEY (branch_id)
        REFERENCES branch(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
        );

            -- Create the Product table
            CREATE TABLE IF NOT EXISTS user_table (
                id INT NOT NULL AUTO_INCREMENT,
                user_name VARCHAR(100),
                user_lastName VARCHAR(100),
                user_password VARCHAR(100),
                user_email VARCHAR(100),
                user_role VARCHAR(100),
                branch_id INT,
                PRIMARY KEY (id),
                FOREIGN KEY (branch_id)
                REFERENCES branch(id)
                ON DELETE CASCADE
                ON UPDATE CASCADE
                );
            -- Create the Product table
            CREATE TABLE IF NOT EXISTS product_sale (
                id INT NOT NULL AUTO_INCREMENT,
                product_sale_price FLOAT,
                product_sale_amount INT,
                product_id INT,
                branch_id INT,
                PRIMARY KEY (id),
                FOREIGN KEY (branch_id)
                REFERENCES branch(id)
                ON DELETE CASCADE
                ON UPDATE CASCADE,
                FOREIGN KEY (product_id)
                 REFERENCES product(id)
                                ON DELETE CASCADE
                                ON UPDATE CASCADE
                );


                          -- Create the Product table
                            CREATE TABLE IF NOT EXISTS sale (
                                id INT NOT NULL AUTO_INCREMENT,
                                product_id INT NOT NULL,
                                product_sale_id INT NOT NULL,
                                sale_uuid VARCHAR(500),

                                PRIMARY KEY (id),
                                FOREIGN KEY (product_id)
                                REFERENCES product(id)
                                ON DELETE CASCADE
                                ON UPDATE CASCADE,
                                     FOREIGN KEY (product_sale_id)
                                       REFERENCES product_sale(id)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE
                                );