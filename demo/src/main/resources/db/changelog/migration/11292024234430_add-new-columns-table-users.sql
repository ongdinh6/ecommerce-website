-- liquibase formatted sql

-- changeset add-new-columns-table-users:1
ALTER TABLE `users`
ADD COLUMN refreshToken VARCHAR(255) NULL,
ADD COLUMN enable BIT DEFAULT 1;
