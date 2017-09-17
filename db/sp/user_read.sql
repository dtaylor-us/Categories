DROP PROCEDURE IF EXISTS user_read;

DELIMITER $$

CREATE PROCEDURE user_read(
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
                    a.password
        FROM user a
        WHERE a.userID = userID;
        END;

	ELSEIF (QueryID = 20) THEN
        BEGIN
        SELECT 	a.userID,
                    a.userName,
                    a.emailAddress,
                    a.psalt,
                    a.password
                    
        FROM user a;
        END;
	END IF;

END $$

DELIMITER ;