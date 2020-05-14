CREATE TABLE product_type
(
    id SERIAL PRIMARY KEY NOT NULL,
    created_at    timestamp    NOT NULL,
    created_by    BIGINT       NOT NULL,
    deleted_at    timestamp null,
    updated_at    timestamp null,
    name  VARCHAR(225) NOT NULL,
    code  VARCHAR(225) NOT NULL
);
INSERT INTO product_type (CREATED_AT, CREATED_BY, name, code)
VALUES
(CURRENT_TIMESTAMP, 0, 'Zakat Emas, Perak, dan logam mulia lainnya', 'emas'),
(CURRENT_TIMESTAMP, 0, 'Zakat Perniagaan', 'perniagaan'),
(CURRENT_TIMESTAMP, 0, 'Zakat Pendapat dan jasa', 'pendapatan'),
(CURRENT_TIMESTAMP, 0, 'Zakat Pertanian, Perkebunan, dan kehutanan', 'pertanian'),
(CURRENT_TIMESTAMP, 0, 'Zakat Uang dan surat berharga lainnya', 'uang');