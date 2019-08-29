BEGIN TRANSACTION;
CREATE TABLE member (
    id      BIGINT IDENTITY
    PRIMARY KEY,
    created_at DATETIME2 NOT NULL,
    created_by BIGINT NOT NULL,
    updated_at DATETIME2,
    deleted_at DATETIME2,
    name VARCHAR(255) NOT NULL,
    dob_lace VARCHAR(255),
    birth_Date DATETIME2 NOT NULL,
    job VARCHAR (255) NOT NULL
);
COMMIT;