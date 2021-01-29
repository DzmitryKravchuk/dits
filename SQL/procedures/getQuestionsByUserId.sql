USE `dits_test`;
DROP procedure IF EXISTS `getQuestionsByUserId`;

DELIMITER $$
USE `dits_test`$$
CREATE PROCEDURE `getQuestionsByUserId` (user_id INT)
BEGIN
SELECT * FROM dits_test.question where questionId IN (SELECT questionId FROM dits_test.statistic where userId=user_id GROUP BY  questionId);
END$$

DELIMITER ;