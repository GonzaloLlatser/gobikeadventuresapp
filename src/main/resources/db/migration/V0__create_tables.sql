CREATE SCHEMA IF NOT EXISTS public;

SET search_path TO public;


-- ROLES
CREATE TABLE role (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    permission_description VARCHAR(255)
);

-- USERS
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id BIGINT,
    CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES role(id)
);

-- BIKES
CREATE TABLE bike (
    id BIGSERIAL PRIMARY KEY,
    status VARCHAR(50) NOT NULL,
    type VARCHAR(50),
    serial_number VARCHAR(255) UNIQUE,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    year VARCHAR(50),
    color VARCHAR(50)
);

-- COSTS
CREATE TABLE cost (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    value_per_hour DOUBLE PRECISION NOT NULL
);

-- TYPE EXPERIENCE
CREATE TABLE type_experience (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    cost_id BIGINT NOT NULL,
    CONSTRAINT fk_type_experience_cost FOREIGN KEY (cost_id) REFERENCES cost(id)
);

-- EXPERIENCES
CREATE TABLE experience (
    id BIGSERIAL PRIMARY KEY,
    type_id BIGINT NOT NULL,
    bike_id BIGINT,
    CONSTRAINT fk_experience_type FOREIGN KEY (type_id) REFERENCES type_experience(id),
    CONSTRAINT fk_experience_bike FOREIGN KEY (bike_id) REFERENCES bike(id)
);

-- PAYMENT METHODS
CREATE TABLE payment_method (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    provider VARCHAR(255)
);

-- PAYMENTS
CREATE TABLE payment (
    id BIGSERIAL PRIMARY KEY,
    transaction_id VARCHAR(255) NOT NULL UNIQUE,
    date TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL
);

-- PERSONAL DATA
CREATE TABLE personal_data (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    address VARCHAR(255),
    phone_primary VARCHAR(50) NOT NULL,
    phone_secondary VARCHAR(50),
    payment_method_id BIGINT,
    CONSTRAINT fk_personal_data_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_personal_data_payment_method FOREIGN KEY (payment_method_id) REFERENCES payment_method(id)
);

-- RESERVATIONS
CREATE TABLE reservation (
    id BIGSERIAL PRIMARY KEY,
    reservation_date TIMESTAMP,
    user_id BIGINT NOT NULL,
    payment_id BIGINT,
    experience_id BIGINT,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_reservation_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_reservation_payment FOREIGN KEY (payment_id) REFERENCES payment(id),
    CONSTRAINT fk_reservation_experience FOREIGN KEY (experience_id) REFERENCES experience(id)
);
