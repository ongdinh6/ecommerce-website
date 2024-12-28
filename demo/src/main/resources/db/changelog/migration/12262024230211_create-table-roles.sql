-- liquibase formatted sql

-- changeset omdinh:12262024230211_create-table-roles
CREATE TABLE IF NOT EXISTS `roles` (
    name VARCHAR(10) NOT NULL PRIMARY KEY,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    createdBy VARCHAR(255) NOT NULL,
    activated BIT DEFAULT TRUE
);
