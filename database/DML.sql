
INSERT INTO Persons (first_name, last_name) VALUES ('Anna', 'Nagy');
INSERT INTO Addresses (person_id, zip, city, street, is_permanent) VALUES (1, 1245, 'Budapest', 'Kossuth utca 1', 1);
INSERT INTO Contacts (person_id, email, telephone) VALUES (1, 'anna.nagy@email.com', '+36 30 233 3257');

INSERT INTO Persons (first_name, last_name) VALUES ('Gábor', 'Kovács');
INSERT INTO Addresses (person_id, zip, city, street, is_permanent) VALUES (2, 5689, 'Debrecen', 'Piac utca 23', 1);
INSERT INTO Contacts (person_id, email, telephone) VALUES (2, 'gabor.kovacs@email.com', '+36 20 435 2678');

INSERT INTO Persons (first_name, last_name) VALUES ('Judit', 'Molnár');
INSERT INTO Addresses (person_id, zip, city, street, is_permanent) VALUES (3, 9865, 'Szeged', 'Dugonics tér 5', 1);
INSERT INTO Addresses (person_id, zip, city, street, is_permanent) VALUES (3, 67890, 'San Francisco', 'Market St 123', 0);
INSERT INTO Contacts (person_id, email, telephone) VALUES (3, 'judit.molnar@email.com', '+36 30 724 7564');

INSERT INTO Persons (first_name, last_name) VALUES ('Márton', 'Varga');
INSERT INTO Addresses (person_id, zip, city, street, is_permanent) VALUES (4, 2356, 'Pécs', 'Szent István tér 12', 0);
INSERT INTO Addresses (person_id, zip, city, street, is_permanent) VALUES (4, 34567, 'Chicago', 'Main St 789', 1);
INSERT INTO Contacts (person_id, email, telephone) VALUES (4, 'marton.varga@email.com', '+36 30 543 3452');

INSERT INTO Persons (first_name, last_name) VALUES ('Réka', 'Szabó');
INSERT INTO Addresses (person_id, zip, city, street, is_permanent) VALUES (5, 8754, 'Győr', 'Baross utca 56', 1);
INSERT INTO Contacts (person_id, email, telephone) VALUES (5, 'reka.szabo@email.com', '+36 70 267 3674');
