-- liquibase formatted sql

-- changeset alter-table-orders:2
ALTER TABLE `orders`
DROP COLUMN `updatedAt`,
ADD COLUMN `updatedAt` DATETIME NULL;
