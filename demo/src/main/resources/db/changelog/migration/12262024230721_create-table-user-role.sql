-- liquibase formatted sql

-- changeset omdinh:12262024230721_create-table-user-role
CREATE TABLE IF NOT EXISTS `user_role`(
    email VARCHAR(255),
    role VARCHAR(10),
    FOREIGN KEY (email) REFERENCES `users`(email),
    FOREIGN KEY (role) REFERENCES `roles`(name)
);
