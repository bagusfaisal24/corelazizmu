BEGIN TRANSACTION;
CREATE TABLE email
(
    id  BIGINT IDENTITY UNSIGNED AUTO_INCREMENT
        PRIMARY KEY,
    created_at    timestamp    NOT NULL,
    created_by    BIGINT       NOT NULL,
    deleted_at    timestamp,
    updated_at    timestamp,
    x_message_id  VARCHAR(225),
    subject       VARCHAR(125) NOT NULL,
    task_id       VARCHAR(125) NOT NULL,
    status        VARCHAR(125) NOT NULL,
    email_to      VARCHAR(125) NOT NULL,
    email_to_name VARCHAR(125),
    type          VARCHAR(125),
    requested_at  timestamp    NOT NULL
);
COMMIT;