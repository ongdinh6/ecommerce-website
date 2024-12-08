-- liquibase formatted sql

-- changeset create-table-products:1
CREATE TABLE IF NOT EXISTS `products`(
    id VARCHAR(255) NOT NULL PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(255),
    thumbnail VARCHAR(255),
    description NVARCHAR(1000),
    category VARCHAR(255),
    price DOUBLE,
    type VARCHAR(255),
    discount DOUBLE,
    published BIT,
    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME,
    createdBy VARCHAR(255) DEFAULT 'SYSTEM',
    updatedBy VARCHAR(255)
);
