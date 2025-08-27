INSERT INTO roles (name)
VALUES
    ('user'),
    ('admin');

INSERT INTO customer_roles (customer_id, role_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 2),
    (5, 2);