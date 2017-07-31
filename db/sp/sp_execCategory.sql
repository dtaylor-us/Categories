
DROP PROCEDURE IF EXISTS ExecCategory;

DELIMITER $$

CREATE PROCEDURE ExecCategory(
IN queryID INT,
	IN vCategoryID INT,
    IN categoryName VARCHAR(50), 
    IN emailAddress VARCHAR(80),
    IN psalt BINARY(64),
    IN password CHAR(40)
)
BEGIN
	
    # INSERT
	IF(queryID = 10) THEN 
		BEGIN
			INSERT INTO category(	categoryID,
                                    categoryName,
                                    categoryDesc)
			VALUES (	categoryID,
                        categoryName,
                        categoryDesc);
                        
			SELECT LAST_INSERT_ID();
        END;
	
    # UPDATE
	ELSEIF (queryID = 20) THEN
		BEGIN
			UPDATE 	category a
			SET		a.categoryID = IFNULL(categoryID, a.categoryID),
                    a.categoryName = IFNULL(categoryName, a.categoryName),
                    a.categoryDesc = IFNULL(categoryDesc, a.categoryDesc),
			WHERE 	a.categoryID = vCategoryID;
            
            SELECT ROW_COUNT();
        END;
        
	# DELETE
    ELSEIF (queryID = 30) THEN
		BEGIN
			DELETE	
            FROM	category
            WHERE 	categoryID = vCategoryID;
            
            SELECT ROW_COUNT();
        END;
        
	END IF;

END $$

DELIMITER ;