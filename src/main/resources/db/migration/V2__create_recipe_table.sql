CREATE TABLE `recipe`
(
	`recipe_id`	int		NOT NULL AUTO_INCREMENT,
	`name`		varchar(255)	NOT NULL,
	`detail`	varchar(255)	NOT NULL,
	PRIMARY KEY (`recipe_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;