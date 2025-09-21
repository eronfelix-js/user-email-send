DROP TABLE IF EXISTS tb_email;

CREATE TABLE tb_email (
    email_id UUID PRIMARY KEY,
    user_id UUID,
    email_from VARCHAR(255),
    email_to VARCHAR(255),
    email_subject VARCHAR(255),
    email_body TEXT,
    send_date TIMESTAMP,
    email_status VARCHAR(50)
);
