CREATE TABLE  app_user (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    created_at timestamp NOT NULL,
    created_by BIGINT NOT NULL,
    updated_at timestamp NULL,
    deleted_at timestamp NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR (255),
    full_name VARCHAR (255) NOT NULL,
    enable bit default 0,
    user_role VARCHAR(255) NOT NULL
);