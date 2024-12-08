-- liquibase formatted sql

-- changeset create-table-order_product:1
CREATE TABLE IF NOT EXISTS `order_product`(
    order_id VARCHAR(255) NOT NULL,
    product_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES `orders`(id),
    FOREIGN KEY (product_id) REFERENCES `products`(id)
);
