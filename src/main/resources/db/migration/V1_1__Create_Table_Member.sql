CREATE TABLE  member (
    id SERIAL PRIMARY KEY NOT NULL,
    created_at timestamp NOT NULL,
    created_by BIGINT NOT NULL,
    updated_at timestamp NULL,
    deleted_at timestamp NULL,
    name VARCHAR(255) NOT NULL,
    dob_place VARCHAR(255) NOT NULL,
    birth_date timestamp NULL,
    job VARCHAR (255) NOT NULL
);