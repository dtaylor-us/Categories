DROP PROCEDURE IF EXISTS GetCategory;

DELIMITER $$

CREATE PROCEDURE GetCategory(
	IN queryID int,
	IN categoryID int
)
BEGIN
	
	IF(QueryID = 10) THEN 
		BEGIN
                SELECT 	a.categoryID,
                        a.imageID,
                        a.categoryName,
                        a.categoryDesc,
                        a.dateCreated
			FROM category a
        WHERE a.categoryID = categoryID;
        END;

	ELSEIF (QueryID = 20) THEN
		BEGIN
                SELECT 	a.categoryID,
                        a.imageID,
                        a.categoryName,
                        a.categoryDesc,
                        a.dateCreated
			FROM category a;
        END;
	END IF;

END $$

DELIMITER ;