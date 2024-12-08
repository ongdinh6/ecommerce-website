-- liquibase formatted sql

-- changeset alter-table-order_product:1
DROP TABLE IF EXISTS `order_product`;

CREATE TABLE IF NOT EXISTS `order_product`(
    orderId VARCHAR(255) NOT NULL,
    productId VARCHAR(255) NOT NULL,
    FOREIGN KEY (orderId) REFERENCES `orders`(id),
    FOREIGN KEY (productId) REFERENCES `products`(id)
);
