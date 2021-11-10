CREATE TABLE IF NOT EXISTS URL (
    ID              BIGSERIAL PRIMARY KEY,
    SHORT_URL       VARCHAR(200) NOT NULL,
    LONG_URL        VARCHAR(200) NOT NULL,
    EXPIRATION_DATE TIMESTAMP DEFAULT NOW()
    )