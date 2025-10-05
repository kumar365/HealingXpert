-- Drop existing column if exists and recreate with proper type
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    image_data BYTEA NULL,
    -- other columns will be created by Hibernate
    version INTEGER,
    created_by VARCHAR(255),
    created_date TIMESTAMP,
    ip_address VARCHAR(255),
    modified_by VARCHAR(255),
    modified_date TIMESTAMP,
    age VARCHAR(255),
    date_of_birth DATE,
    date_of_birth_string VARCHAR(255),
    display_name VARCHAR(255),
    email VARCHAR(255),
    enabled BOOLEAN,
    first_name VARCHAR(255),
    gender VARCHAR(255),
    last_login TIMESTAMP,
    password VARCHAR(255),
    phone_number VARCHAR(255),
    profile_image_name VARCHAR(255),
    provider VARCHAR(255),
    provider_user_id VARCHAR(255),
    token VARCHAR(255),
    token_creation_date TIMESTAMP,
    user_type VARCHAR(255),
    username VARCHAR(255)
);
