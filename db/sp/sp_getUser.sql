DROP PROCEDURE IF EXISTS GetUser;

DELIMITER $$

CREATE PROCEDURE GetUser(
	IN queryID int,
	IN userID int
)
BEGIN
	
	IF(QueryID = 10) THEN 
		BEGIN
			SELECT 	a.userID,
                    a.userName,
                    a.emailAddress,
                    a.psalt,
                    a.password,
                    a.dateCreated
			FROM user a
        WHERE a.userID = userID;
        END;

	ELSEIF (QueryID = 20) THEN
		BEGIN
			SELECT 	a.userID,
                    a.userName,
                    a.emailAddress,
                    a.psalt,
                    a.password,
                    a.dateCreated
                    
			FROM user a;
        END;
	END IF;

END $$

DELIMITER ;