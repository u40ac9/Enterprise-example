CREATE TABLE app.motif(
	ID INTEGER PRIMARY KEY default NULL,
	Name VarChar(40) default NULL,
	Price DOUBLE default NULL,
	Quantity INTEGER default NULL
);

CREATE TABLE app.customer(
	ID INTEGER PRIMARY KEY default NULL,
	Name VarChar(40) default NULL,
	Address VarChar(60) default NULL,
	Password VarChar(8) default NULL
);



INSERT INTO app.motif (ID, Name, Price, Quantity) VALUES (1,'Butterfly',0.5,200);
INSERT INTO app.motif (ID, Name, Price, Quantity) VALUES (2,'Aeroplane',0.6,130);
INSERT INTO app.motif (ID, Name, Price, Quantity) VALUES (3,'Pacman',0.4,100);
INSERT INTO app.motif (ID, Name, Price, Quantity) VALUES (4,'Computer Mouse',0.4,100);
INSERT INTO app.motif (ID, Name, Price, Quantity) VALUES (5,'Guitar',0.6,55);

CREATE TABLE app.orders (
	ID INTEGER PRIMARY KEY default NULL,
	MotifID INTEGER default NULL,
	Quantity INTEGER default NULL,
	Price DOUBLE default NULL,
	CustomerID INTEGER default NULL
); 


INSERT INTO app.orders (ID, MotifID, Quantity, Price, CustomerID) VALUES (1, 2, 20, 5.5, 1);


CREATE TABLE app.staff (
	ID INTEGER PRIMARY KEY default NULL,
	Name VarChar(20) default NULL,
	Password VarChar(20) default NULL
); 

INSERT INTO app.staff (ID, Name, Password) VALUES (1, 'Andrea', 'hello');
INSERT INTO app.staff (ID, Name, Password) VALUES (2, 'Marius', 'hello');

SELECT * FROM app.motif WHERE ID = 1;
