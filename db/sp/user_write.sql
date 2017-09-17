DROP PROCEDURE IF EXISTS user_write;

DELIMITER $$

CREATE PROCEDURE user_write(
IN queryID INT,
IN vUserID INT,
IN userName VARCHAR(50), 
IN emailAddress VARCHAR(80),
IN password CHAR(40)
)
BEGIN
	
    # INSERT
	IF(queryID = 10) THEN 
		BEGIN
			INSERT INTO user(	userID,
                                userName,
                                emailAddress
                                password)
			VALUES (	userID,
                        userName,
                        emailAddress,
                        password);
                        
			SELECT LAST_INSERT_ID();
        END;
	
    # UPDATE
	ELSEIF (queryID = 20) THEN
		BEGIN
			UPDATE 	user a
			SET		a.userID = IFNULL(userID, a.userID),
                    a.userName = IFNULL(userName, a.userName),
                    a.emailAddress = IFNULL(emailAddress, a.emailAddress),
                    a.password = IFNULL(password, a.password)
			WHERE 	a.userID = vUserID;
            
            SELECT ROW_COUNT();
        END;
        
	# DELETE
    ELSEIF (queryID = 30) THEN
		BEGIN
			DELETE	
            FROM	user
            WHERE 	userID = vUserID;
            
            SELECT ROW_COUNT();
        END;
        
	END IF;

END $$

DELIMITER ;