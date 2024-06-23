CREATE TABLE User (
	idUser int NOT NULL AUTO_INCREMENT,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    
    PRIMARY KEY (idUser),
    UNIQUE(username)
);

CREATE TABLE room (
	idRoom int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    ownerId int NOT NULL,
    
    PRIMARY KEY(idRoom),
    FOREIGN KEY (ownerId) REFERENCES User(idUser)
)