-- liquibase formatted sql

-- changeSet see1rg:1

CREATE TABLE socks (
                       id SERIAL PRIMARY KEY,
                       color VARCHAR(50) NOT NULL,
                       cotton_part INTEGER NOT NULL,
                       quantity INTEGER NOT NULL
);

