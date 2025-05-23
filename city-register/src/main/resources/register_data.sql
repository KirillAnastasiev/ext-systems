INSERT INTO cr_district
    (district_code, district_name)
VALUES
    (1, 'Выборгский');

INSERT INTO cr_street
    (street_code, street_name)
VALUES
    (1, 'Сампсоньевский проспект');

INSERT INTO cr_address
    (district_code, street_code, building, extension, apartment)
VALUES
    (1, 1, '10', '2', '121');

INSERT INTO cr_person
    (sur_name, given_name, patronymic, date_of_birth, passport_seria, passport_number, passport_date, certificate_number, certificate_date)
VALUES
    ('Васильев', 'Павел', 'Николаевич', '1995-03-18', '1234', '123456', '2015-04-11', NULL, NULL),
    ('Васильева', 'Ирина', 'Петровна', '1997-08-21', '4321', '654321', '2017-09-19', NULL, NULL),
    ('Васильева', 'Евгения', 'Павловна', '2016-01-11', NULL, NULL, NULL, '456123', '2016-01-21'),
    ('Васильев', 'Александр', 'Павлович', '2018-10-24', NULL, NULL, NULL, '321654', '2018-11-09');

INSERT INTO cr_address_person
    (address_id, person_id, start_date, end_date)
VALUES
    (1, 1, '2014-10-12', NULL),
    (1, 2, '2014-10-12', NULL),
    (1, 3, '2016-02-05', NULL),
    (1, 4, '2018-12-11', NULL);
