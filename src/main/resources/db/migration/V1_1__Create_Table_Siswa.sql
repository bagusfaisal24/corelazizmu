BEGIN TRANSACTION;
CREATE TABLE siswa (
    id      BIGINT IDENTITY
    PRIMARY KEY,
    created_at DATETIME2 NOT NULL,
    created_by BIGINT NOT NULL,
    updated_at DATETIME2,
    deleted_at DATETIME2,
    nama VARCHAR(255) NOT NULL,
    kelas VARCHAR (255) NOT NULL
);
COMMIT;