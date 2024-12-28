-- liquibase formatted sql

-- changeset omdinh:12262024231612_insert-data-into-roles-table
INSERT INTO `roles`(name, createdBy)
VALUES ('ADMIN', 'omdinh'),
    ('USER', 'omdinh');
