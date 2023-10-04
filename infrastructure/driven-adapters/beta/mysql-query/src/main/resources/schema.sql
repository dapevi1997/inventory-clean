USE prueba1;

-- Create the Branch table
CREATE TABLE IF NOT EXISTS branch (
    branch_id VARCHAR(500) NOT NULL,
    branch_name VARCHAR(100),
    branch_country VARCHAR(100),
    branch_city VARCHAR(100),
    PRIMARY KEY (branch_id)
    );
    -- Create the Product table
    CREATE TABLE IF NOT EXISTS product (
        product_id VARCHAR(500) NOT NULL,
        product_name VARCHAR(100),
        product_description VARCHAR(100),
        product_price FLOAT,
        product_inventory_stock INTEGER,
        product_category VARCHAR(100),
        branch_id VARCHAR(500) NOT NULL,
        PRIMARY KEY (product_id),
        FOREIGN KEY (branch_id)
        REFERENCES branch(branch_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
        );

            -- Create the Product table
            CREATE TABLE IF NOT EXISTS user_table (
                user_id VARCHAR(500) NOT NULL,
                user_name VARCHAR(100),
                user_lastName VARCHAR(100),
                user_password VARCHAR(100),
                user_email VARCHAR(100),
                user_role VARCHAR(100),
                branch_id VARCHAR(500) NOT NULL,
                PRIMARY KEY (user_id),
                FOREIGN KEY (branch_id)
                REFERENCES branch(branch_id)
                ON DELETE CASCADE
                ON UPDATE CASCADE
                );