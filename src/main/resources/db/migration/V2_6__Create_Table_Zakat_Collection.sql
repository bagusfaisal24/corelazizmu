CREATE TABLE  zakat_collection (
    id SERIAL PRIMARY KEY NOT NULL,
    created_at timestamp NOT NULL,
    created_by BIGINT NOT NULL,
    updated_at timestamp NULL,
    deleted_at timestamp NULL,
    member_id int4 REFERENCES member (id) ON DELETE RESTRICT,
    product_type_id int4 REFERENCES product_type (id) ON DELETE RESTRICT,
    amount float NOT NULL,
    submit_date timestamp NOT NULL
);