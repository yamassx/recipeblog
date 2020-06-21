ALTER TABLE `recipe` ADD CONSTRAINT `user_id_fk`
FOREIGN KEY (`user_id`)
REFERENCES `users`(`id`);