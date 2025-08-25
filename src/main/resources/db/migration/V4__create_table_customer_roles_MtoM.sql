CREATE TABLE customer_roles (
    customer_id INT REFERENCES customer (id),
    role_id INT REFERENCES roles (id),
    PRIMARY KEY (customer_id, role_id)
)