DROP TABLE IF EXISTS products;

CREATE TABLE  products
(
    id      INTEGER IDENTITY PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    price   BIGINT NOT NULL
    );