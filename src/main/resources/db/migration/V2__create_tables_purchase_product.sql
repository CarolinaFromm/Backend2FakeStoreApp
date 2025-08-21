CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    price NUMERIC (10,2),
    description TEXT,
    category VARCHAR(255),
    rate NUMERIC(3,1),
    count INT
);

CREATE TABLE purchase (
    id SERIAL PRIMARY KEY,
    customer_id INT,
    purchase_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (purchase_id) REFERENCES purchase(id)
)