-- liquibase formatted sql

-- changeset create-table-users:1
CREATE TABLE IF NOT EXISTS `users`(
    email VARCHAR(255) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    fullName VARCHAR(255),
    createdAt DATETIME DEFAULT NOW()
);
