INSERT INTO persons(name, taj, birthdate, birthplace, email, tax_id) VALUES ('Nagy Erik', 4537485, '2000-02-21', 'Budapest', 'nagy.erik@email.com', 7786443);
INSERT INTO persons(name, taj, birthdate, birthplace, email, tax_id) VALUES ('Kovács János', 3657485, '2000-02-21', 'Budapest', 'kovacs.janos@email.com', 7754342);
INSERT INTO persons(name, taj, birthdate, birthplace, email, tax_id) VALUES ('Kiss Lajos', 4678482, '2000-12-01', 'Budapest', 'kiss.lajos@email.com', 7312332);
INSERT INTO persons(name, taj, birthdate, birthplace, email, tax_id) VALUES ('Ferenc Anna', 4537655, '2000-03-11', 'Budapest', 'ferenc.anna@email.com', 7786441);

INSERT INTO addresses(city, house_number, street, zip_code, person_id) VALUES ('Budapest', 5, 'Füge utca', 1010, (SELECT id FROM persons WHERE name = 'Nagy Erik'));
INSERT INTO addresses(city, house_number, street, zip_code, person_id) VALUES ('Budapest', 46, 'Szentendrei út', 1032, (SELECT id FROM persons WHERE name = 'Kovács János'));
INSERT INTO addresses(city, house_number, street, zip_code, person_id) VALUES ('Budapest', 13, 'Dézsa út', 1114, (SELECT id FROM persons WHERE name = 'Kiss Lajos'));
INSERT INTO addresses(city, house_number, street, zip_code, person_id) VALUES ('Budapest', 12, 'Alma utca', 1068, (SELECT id FROM persons WHERE name = 'Kiss Lajos'));
INSERT INTO addresses(city, house_number, street, zip_code, person_id) VALUES ('Budapest', 4, 'Körte utca', 1010, (SELECT id FROM persons WHERE name = 'Ferenc Anna'));

INSERT INTO person_phone_numbers(person_id, phone_numbers) VALUES ((SELECT id FROM persons WHERE name = 'Nagy Erik'), '06201234567');
INSERT INTO person_phone_numbers(person_id, phone_numbers) VALUES ((SELECT id FROM persons WHERE name = 'Kovács János'), '06301114568');
INSERT INTO person_phone_numbers(person_id, phone_numbers) VALUES ((SELECT id FROM persons WHERE name = 'Kiss Lajos'), '06301233255');
INSERT INTO person_phone_numbers(person_id, phone_numbers) VALUES ((SELECT id FROM persons WHERE name = 'Ferenc Anna'), '06701145363');