
CREATE TABLE user (
	userID INT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(50), 
    emailAddress VARCHAR(80),
    imageID INT,
    psalt BINARY(64),
    password CHAR(40),
    PRIMARY KEY (userID)
);

CREATE TABLE category (
	categoryID INT NOT NULL AUTO_INCREMENT,
    imageID INT,
    categoryName VARCHAR(100),
    categoryDesc VARCHAR(8000),
    PRIMARY KEY (categoryID)
);

CREATE TABLE userCategory (
    userID INT NOT NULL,
    categoryID INT NOT NULL,
    KEY fk_userCategory_category_idx (categoryID),
    CONSTRAINT fk_userCategory_user FOREIGN KEY (userID) REFERENCES user (userID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_userCategory_category FOREIGN KEY (categoryID) REFERENCES category (categoryID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE item (
	itemID INT NOT NULL AUTO_INCREMENT,
    imageID INT,
    userID INT,
    itemName VARCHAR (500),
    itemDesc VARCHAR(8000),
    PRIMARY KEY (itemID)
);

CREATE TABLE image (
	imageID INT NOT NULL AUTO_INCREMENT,
	caption VARCHAR(45) NOT NULL,
	img LONGBLOB NOT NULL,
    PRIMARY KEY (imageID)
);

CREATE TABLE categoryItem (
    categoryID INT NOT NULL,
    itemID INT NOT NULL,
    PRIMARY KEY (categoryID, itemID),
    KEY fk_categoryItem_item_idx (itemID),
    CONSTRAINT fk_categoryItem_category FOREIGN KEY (categoryID) REFERENCES category (categoryID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_categoryItem_item FOREIGN KEY (itemID) REFERENCES item (itemID) ON DELETE CASCADE ON UPDATE CASCADE
);