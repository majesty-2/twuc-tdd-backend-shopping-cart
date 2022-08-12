CREATE TABLE products
(
    id       int PRIMARY KEY AUTO_INCREMENT,
    title    varchar(1000) not NULL,
    price    decimal       not NULL,
    quantity int           not NULL
);
