CREATE TABLE `users`
(
    `id`        int          NOT NULL AUTO_INCREMENT,
    `name`      varchar(255) NOT NULL,
    `email`     varchar(255) NOT NULL,
    `is_active` tinyint(1)   NOT NULL DEFAULT '1',
    `password`  varchar(255) NOT NULL DEFAULT 'aaa',
    `login_id`  varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);