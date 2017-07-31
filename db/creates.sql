CREATE TABLE user (
	userID INT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(50), 
    emailAddress VARCHAR(80),
    psalt BINARY(64),
    password CHAR(40),
    dateCreated DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (userID)
);

CREATE TABLE category (
	categoryID INT NOT NULL AUTO_INCREMENT,
    imageID INT,
    categoryName VARCHAR(100),
    categoryDesc VARCHAR(8000),
    dateCreated DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (categoryID)
);

CREATE TABLE userCategory (
	userCategoryID INT NOT NULL AUTO_INCREMENT,
    userID INT,
    categoryID INT,
	dateCreated DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (userCategoryID)
);

CREATE TABLE item (
	itemID INT NOT NULL AUTO_INCREMENT,
    imageID INT,
    userID INT,
    itemName VARCHAR (500),
    itemDesc VARCHAR(8000),
	dateCreated DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (itemID)
);

CREATE TABLE image (
	imageID INT NOT NULL AUTO_INCREMENT,
	caption VARCHAR(45) NOT NULL,
	img LONGBLOB NOT NULL,
    dateCreated DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (imageID)
);

CREATE TABLE categoryItem (
	categoryItemID INT NOT NULL AUTO_INCREMENT,
    categoryID INT,
    itemID INT,
	dateCreated DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (categoryItemID)
);