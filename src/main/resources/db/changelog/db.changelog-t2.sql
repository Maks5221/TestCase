--liquibase formatted sql

--changeset Maksim:1
CREATE TABLE IF NOT EXISTS goods
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(255) NOT NULL ,
    description VARCHAR(4096),
    price FLOAT DEFAULT 0,
    availability VARCHAR(255) DEFAULT 'ABSENT'
);