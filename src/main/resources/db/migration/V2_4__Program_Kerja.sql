CREATE TABLE program_kerja
(
    id SERIAL PRIMARY KEY NOT NULL,
    created_at    timestamp    NOT NULL,
    created_by    BIGINT       NOT NULL,
    deleted_at    timestamp null,
    updated_at    timestamp null,
    name  VARCHAR(225)
);