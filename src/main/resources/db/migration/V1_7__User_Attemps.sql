CREATE TABLE  user_attemps (
                           id SERIAL PRIMARY KEY NOT NULL,
                           created_at timestamp NOT NULL,
                           created_by BIGINT NOT NULL,
                           updated_at timestamp NULL,
                           deleted_at timestamp NULL,
                           username VARCHAR(255) NOT NULL,
                           attemps INT NOT NULL,
                           last_modified timestamp NULL
);