ALTER TABLE category ADD CONSTRAINT FK_category_imageID FOREIGN KEY(imageID) REFERENCES image(imageID);    

ALTER TABLE user ADD CONSTRAINT FK_user_imageID FOREIGN KEY(imageID) REFERENCES image(imageID);

ALTER TABLE userCategory ADD CONSTRAINT FK_userCategory_userID FOREIGN KEY(userID) REFERENCES user(userID);
ALTER TABLE userCategory ADD CONSTRAINT FK_userCategory_categoryID FOREIGN KEY(categoryID) REFERENCES category(categoryID);

ALTER TABLE item ADD CONSTRAINT FK_item_imageID FOREIGN KEY(imageID) REFERENCES image(imageID);
ALTER TABLE item ADD CONSTRAINT FK_item_userID FOREIGN KEY(userID) REFERENCES user(userID);

ALTER TABLE categoryItem ADD CONSTRAINT FK_categoryItem_categoryID FOREIGN KEY(categoryID) REFERENCES category(categoryID);
ALTER TABLE categoryItem ADD CONSTRAINT FK_categoryItem_itemID FOREIGN KEY(itemID) REFERENCES item(itemID);



export MIGRATOIN_DB_URL=jdbc:mysql://us-cdbr-iron-east-03.cleardb.net:3306/heroku_4e22acf2c863394
export MIGRATOIN_DB_USER=be5a5f924b1322
export MIGRATION_DB_PASSWORD=9766bc85