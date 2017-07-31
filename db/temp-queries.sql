select * from user;
select * from category;

INSERT INTO user(userName, emailAddress)
VALUES('dtaylo56', 'derektaylor.us@gmail.com');

INSERT INTO userCategory(userID, categoryID)
VALUES(1,2);

INSERT INTO item(itemName, itemDesc)
VALUES('HeroItem', 'This is a hero item');


SELECT * FROM item
WHERE itemID = 1;


INSERT INTO categoryItem(itemID, categoryID)
VALUES(1,1);


INSERT INTO category(categoryName, categoryDesc)
VALUES('Star Wars', 'All things Star Wars');


SELECT c.categoryName, c.categoryDesc
FROM userCategory a
INNER JOIN user b ON a.userID = b.userID
INNER JOIN category c ON c.categoryID = a.categoryID;

SELECT c.categoryName as Category, b.itemName as Item, d.userName as User
FROM categoryItem a
INNER JOIN item b ON a.itemID = b.itemID
INNER JOIN category c ON c.categoryID = a.categoryID
INNER JOIN user d ON b.userID = d.userID;