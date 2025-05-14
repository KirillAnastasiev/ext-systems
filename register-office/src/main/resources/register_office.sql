DROP TABLE IF EXISTS ro_marriage_certificate;
DROP TABLE IF EXISTS ro_birth_certificate;
DROP TABLE IF EXISTS ro_passport;
DROP TABLE IF EXISTS ro_person;

CREATE TABLE IF NOT EXISTS ro_person (
    person_id                   SERIAL           PRIMARY KEY,
    sex                         SMALLINT         NOT NULL,
    first_name                  VARCHAR(100)     NOT NULL,
    last_name                   VARCHAR(100)     NOT NULL,
    patronymic                  VARCHAR(100)     NOT NULL,
    date_birth                  DATE             NOT NULL
);

CREATE TABLE IF NOT EXISTS ro_passport (
    passport_id                 SERIAL           PRIMARY KEY,
    person_id                   INTEGER          NOT NULL            REFERENCES ro_person(person_id) ON DELETE RESTRICT,
    seria                       VARCHAR(10)      NOT NULL,
    number                      VARCHAR(10)      NOT NULL,
    date_issue                  DATE             NOT NULL,
    issue_department            VARCHAR(200)
);

CREATE TABLE ro_birth_certificate (
    birth_certificate_id        SERIAL           PRIMARY KEY,
    number_certificate          VARCHAR(20)      NOT NULL,
    date_issue                  DATE             NOT NULL,
    person_id                   INTEGER          NOT NULL            REFERENCES ro_person(person_id) ON DELETE RESTRICT,
    father_id                   INTEGER          REFERENCES ro_person(person_id) ON DELETE RESTRICT,
    mother_id                   INTEGER          REFERENCES ro_person(person_id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS ro_marriage_certificate (
    marriage_certificate_id     SERIAL           PRIMARY KEY,
    number_certificate          VARCHAR(20)      NOT NULL,
    date_issue                  DATE             NOT NULL,
    husband_id                  INTEGER          NOT NULL           REFERENCES ro_person(person_id) ON DELETE RESTRICT,
    wife_id                     INTEGER          NOT NULL           REFERENCES ro_person(person_id) ON DELETE RESTRICT,
    active                      BOOLEAN          DEFAULT FALSE,
    end_date                    DATE
);


INSERT INTO ro_person
    (sex, first_name, last_name, patronymic, date_birth)
VALUES
    (1, 'Елена', 'Васильева', 'Сергеевна', '1998-03-24'),
    (2, 'Олег', 'Васильев', 'Петрович', '1997-10-16'),
    (2, 'Николай', 'Васильев', 'Олегович', '2018-10-21');

INSERT INTO ro_passport
    (person_id, seria, number, date_issue, issue_department)
VALUES
    (1, '40000', '123456', '2018-04-10', 'Department Passport'),
    (2, '50000', '654321', '2018-04-10', 'Department Passport');

INSERT INTO ro_birth_certificate
    (number_certificate, date_issue, person_id, father_id, mother_id)
VALUES
    ('123 Birth', '2019-1-01', 3, 2, 1);

INSERT INTO ro_marriage_certificate
    (number_certificate, date_issue, husband_id, wife_id, active)
VALUES
    ('12345', '2018-3-13', 2, 1, TRUE);