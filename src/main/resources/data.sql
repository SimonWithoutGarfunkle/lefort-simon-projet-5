DROP TABLE IF EXISTS persons, firestations, medicalrecords;

CREATE TABLE persons  (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  firstName VARCHAR(250) NOT NULL,
  lastName VARCHAR(250) NOT NULL,
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
  firstName VARCHAR(250) NOT NULL,
  lastName VARCHAR(250) NOT NULL,
  birthdate DATE NOT NULL,
  medications VARCHAR(250) NOT NULL,
  allergies VARCHAR(250) NOT NULL
);

INSERT INTO persons (firstName, lastName, address, city, zip, phone, mail) VALUES
	('John', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'jaboyd@email.com'),
	('Jacob', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6513', 'drk@email.com'),
	('Tenley', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'tenz@email.com'),
	('Roger', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'jaboyd@email.com'),
	('Felicia', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6544', 'jaboyd@email.com'),
	('Jonanathan', 'Marrack', '29 15th St', 'Culver', '97451', '841-874-6513', 'drk@email.com'),
	('Tessa', 'Carman', '834 Binoc Ave', 'Culver', '97451', '841-874-6512', 'tenz@email.com'),
	('Peter', 'Duncan', '644 Gershwin Cir', 'Culver', '97451', '841-874-6512', 'jaboyd@email.com'),
	('Foster', 'Shepard', '748 Townings Dr', 'Culver', '97451', '841-874-6544', 'jaboyd@email.com'),
	('Tony', 'Cooper', '112 Steppes Pl', 'Culver', '97451', '841-874-6874', 'tcoop@ymail.com'),
	('Lily', 'Cooper', '489 Manchester St', 'Culver', '97451', '841-874-9845', 'lily@email.com'),
	('Sophia', 'Zemicks', '892 Downing Ct', 'Culver', '97451', '841-874-7878', 'soph@email.com'),
	('Warren', 'Zemicks', '892 Downing Ct', 'Culver', '97451', '841-874-7512', 'ward@email.com'),
	('Zach', 'Zemicks', '892 Downing Ct', 'Culver', '97451', '841-874-7512', 'zarc@email.com'),
	('Reginold', 'Walker', '908 73rd St', 'Culver', '97451', '841-874-8547', 'reg@email.com'),
	('Jamie', 'Peters', '908 73rd St', 'Culver', '97451', '841-874-7462', 'jpeter@email.com'),
	('Ron', 'Peters', '112 Steppes Pl', 'Culver', '97451', '841-874-8888', 'jpeter@email.com'),
	('Allison', 'Boyd', '112 Steppes Pl', 'Culver', '97451', '841-874-9888', 'aly@imail.com'),
	('Brian', 'Stelzer', '947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'bstel@email.com'),
	('Shawna', 'Stelzer', '947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'ssanw@email.com'),
	('Kendrik', 'Stelzer', '947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'bstel@email.com'),
	('Clive', 'Ferguson', '748 Townings Dr', 'Culver', '97451', '841-874-6741', 'clivfd@ymail.com'),
	('Eric', 'Cadigan', '951 LoneTree Rd', 'Culver', '97451', '841-874-7458', 'gramps@email.com');

INSERT INTO firestations (address, station) VALUES
	('1509 Culver St', '3'),
	('29 15th St', '2'),
	('834 Binoc Ave', '3'),
	('644 Gershwin Cir', '1'),
	('748 Townings Dr', '3'),
	('112 Steppes Pl', '3'),
	('489 Manchester St', '4'),
	('892 Downing Ct', '2'),
	('908 73rd St', '1'),
	('112 Steppes Pl', '4'),
	('947 E. Rose Dr', '1'),
	('748 Townings Dr', '3'),
	('951 LoneTree Rd', '2');

INSERT INTO medicalrecords (firstName, lastName, birthdate, medications, allergies) VALUES
	('John', 'Boyd', '1984-06-03', '["aznol:350mg", "hydrapermazol:100mg"]', '["nillacilan"]'),
	('Jacob', 'Boyd', '1989-06-03', '["pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"]', '[]'),
	('Tenley', 'Boyd', '2012-02-18', '[]', '["peanut"]'),
	('Roger', 'Boyd', '2017-06-09', '[]', '[]'),
	('Felicia', 'Boyd', '1986-08-01', '["tetracyclaz:650mg"]', '["xilliathal"]'),
	('Jonanathan', 'Marrack', '1989-03-01', '[]', '[]'),
	('Tessa', 'Carman', '2012-02-18', '[]', '[]'),
	('Peter', 'Duncan', '2000-06-09', '[]', '["shellfish"]'),
	('Foster', 'Shepard', '1980-08-01', '[]', '[]'),
	('Tony', 'Cooper', '1994-06-03', '["hydrapermazol:300mg", "dodoxadin:30mg"]', '["shellfish"]'),
	('Lily', 'Cooper', '1994-06-03', '[]', '[]'),
	('Sophia', 'Zemicks', '1988-06-03', '["aznol:60mg", "hydrapermazol:900mg", "pharmacol:5000mg", "terazine:500mg"]', '["peanut", "shellfish", "aznol"]'),
	('Warren', 'Zemicks', '1985-06-03', '[]', '[]'),
	('Zach', 'Zemicks', '2017-06-03', '[]', '[]'),
	('Reginold', 'Walker', '1979-08-30', '["thradox:700mg"]', '["illisoxian"]'),
	('Jamie', 'Peters', '1982-06-03', '[]', '[]'),
	('Ron', 'Peters', '1965-06-04', '[]', '[]'),
	('Allison', 'Boyd', '1965-03-15', '["aznol:200mg"]', '["nillacilan"]'),
	('Brian', 'Stelzer', '1975-06-12', '["ibupurin:200mg", "hydrapermazol:400mg"]', '["nillacilan"]'),
	('Shawna', 'Stelzer', '1980-08-07', '[]', '[]'),
	('Kendrik', 'Stelzer', '2014-06-03', '["noxidian:100mg", "pharmacol:2500mg"]', '[]'),
	('Clive', 'Ferguson', '1994-06-03', '[]', '[]'),
	('Eric', 'Cadigan', '1945-06-08', '["tradoxidine:400mg"]', '[]');



