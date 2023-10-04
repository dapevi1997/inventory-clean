USE prueba1;

-- Create the Branch table
CREATE TABLE IF NOT EXISTS branch (
    branch_id VARCHAR(500) NOT NULL,
    branch_name VARCHAR(100),
    branch_country VARCHAR(100),
    branch_city VARCHAR(100),
    PRIMARY KEY (branch_id)
    );

