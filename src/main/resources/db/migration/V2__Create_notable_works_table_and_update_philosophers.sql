CREATE TABLE IF NOT EXISTS notable_works (
    id BIGINT PRIMARY KEY,
    title VARCHAR(256) NOT NULL,
    url VARCHAR(256),
    philosopher_id BIGINT NOT NULL REFERENCES philosophers (id)
)