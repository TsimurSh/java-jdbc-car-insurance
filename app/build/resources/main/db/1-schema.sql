CREATE TABLE IF NOT EXISTS users
(
    id          BIGSERIAL PRIMARY KEY,
    nick        TEXT        NOT NULL,
    login       TEXT UNIQUE NOT NULL,
    password    TEXT        NOT NULL,
    insert_time TIMESTAMP DEFAULT current_timestamp
);
CREATE INDEX IF NOT EXISTS idx_users_login ON users (login);

CREATE TABLE IF NOT EXISTS vehicles
(
    id          BIGSERIAL PRIMARY KEY,
    login       TEXT NOT NULL REFERENCES users (login),
    brand       TEXT NOT NULL,
    model       TEXT NOT NULL,
    insert_time TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS insurance_offers
(
    id          BIGSERIAL PRIMARY KEY,
    vehicle_id  BIGINT NOT NULL REFERENCES vehicles (id),
    insurer     TEXT   NOT NULL,
    price       FLOAT  NOT NULL,
    insert_time TIMESTAMP DEFAULT current_timestamp
);
CREATE INDEX IF NOT EXISTS idx_insurance_offers_vehicle_id ON insurance_offers (vehicle_id);
