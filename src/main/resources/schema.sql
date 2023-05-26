DROP TABLE IF EXISTS persons, firestations, medicalrecords;

CREATE TABLE persons  (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  firstname VARCHAR(250) NOT NULL,
  lastname VARCHAR(250) NOT NULL,
  address VARCHAR(250) NOT NULL,
  city VARCHAR(250) NOT NULL,
  zip INT NOT NULL,
  phone VARCHAR(250) NOT NULL,
  mail VARCHAR(250) NOT NULL
);

CREATE TABLE firestations (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  address VARCHAR(250) NOT NULL,
  station INT NOT NULL
);

CREATE TABLE medicalrecords (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  firstname VARCHAR(250) NOT NULL,
  lastname VARCHAR(250) NOT NULL,
  birthdate DATE NOT NULL,
  medications VARCHAR(250) NOT NULL,
  allergies VARCHAR(250) NOT NULL
);
