DROP TABLE IF EXISTS cr_address_person;
DROP TABLE IF EXISTS cr_person;
DROP TABLE IF EXISTS cr_address;
DROP TABLE IF EXISTS cr_street;
DROP TABLE IF EXISTS cr_district;

CREATE TABLE IF NOT EXISTS cr_district (
    district_code          INTEGER      NOT NULL PRIMARY KEY,
    district_name          VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS cr_street (
    street_code            INTEGER      NOT NULL PRIMARY KEY,
    street_name            VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS cr_address (
    address_id             SERIAL       NOT NULL PRIMARY KEY,
    district_code          INTEGER      NOT NULL REFERENCES cr_district (district_code) ON DELETE RESTRICT,
    street_code            INTEGER      NOT NULL REFERENCES cr_street (street_code) ON DELETE RESTRICT,
    building               VARCHAR(10)  NOT NULL,
    extension              VARCHAR(10),
    apartment              VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS cr_person (
    person_id              SERIAL       NOT NULL PRIMARY KEY,
    sur_name               VARCHAR(100) NOT NULL,
    given_name             VARCHAR(100) NOT NULL,
    patronymic             VARCHAR(100) NOT NULL,
    date_of_birth          DATE         NOT NULL,
    passport_seria         VARCHAR(10),
    passport_number        VARCHAR(10),
    passport_date          DATE,
    certificate_number     VARCHAR(10),
    certificate_date       DATE
);

CREATE TABLE IF NOT EXISTS cr_address_person (
    person_address_id      SERIAL       NOT NULL PRIMARY KEY,
    address_id             INTEGER      NOT NULL REFERENCES cr_address (address_id) ON DELETE RESTRICT,
    person_id              INTEGER      NOT NULL REFERENCES cr_person (person_id) ON DELETE RESTRICT,
    start_date             DATE         NOT NULL,
    end_date               DATE,
    temporal               BOOLEAN      DEFAULT FALSE
);