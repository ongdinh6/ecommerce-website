-- liquibase formatted sql

-- changeset create-table-orders:1
CREATE TABLE IF NOT EXISTS `orders`(
    id VARCHAR(255) PRIMARY KEY DEFAULT UUID(),
    receiver VARCHAR(255),
    totalCost DOUBLE,
    totalItems INT,
    addressDelivery VARCHAR(255),
    phoneNumber VARCHAR(10),
    status VARCHAR(10),
    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME
);
