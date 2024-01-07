CREATE DATABASE PersonRegistryManager

CREATE TABLE Persons (
    id INT PRIMARY KEY IDENTITY,
    first_name VARCHAR(50),
    last_name VARCHAR(50)
);

CREATE TABLE Addresses (
    id INT PRIMARY KEY IDENTITY,
    person_id INT NOT NULL,
    zip INT,
    city VARCHAR(50),
    street VARCHAR(150),
    is_permanent TINYINT,
    FOREIGN KEY (person_id) REFERENCES Persons(id)
);

CREATE TABLE Contacts (
    id INT PRIMARY KEY IDENTITY,
    person_id INT NOT NULL,
    email VARCHAR(40),
    telephone VARCHAR(20),
    FOREIGN KEY (person_id) REFERENCES Persons(id)
);