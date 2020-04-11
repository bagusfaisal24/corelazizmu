alter table member
    add ranting_id int4 REFERENCES ranting (id) ON DELETE RESTRICT
