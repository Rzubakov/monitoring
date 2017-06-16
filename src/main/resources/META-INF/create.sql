INSERT INTO `mydatabase`.`users` (`email`, `password`, `role`) VALUES ('rzubakov@protonmail.com', '/gnplWM1A3X6t7sLuLjnOnqLZjAYewyEJLoq8+H6I4s=', 'ADMIN');
INSERT INTO `mydatabase`.`users` (`email`, `password`, `role`) VALUES ('robot@bot', '/gnplWM1A3X6t7sLuLjnOnqLZjAYewyEJLoq8+H6I4s=', 'BOT');
INSERT INTO `mydatabase`.`categories` (`description`, `name`) VALUES ('GlobalRoot', 'GlobalRoot');
INSERT INTO `mydatabase`.`categories` (`description`, `name`, `parent_id`, `user_id`) VALUES ('Root', 'Root', '1', '1');
INSERT INTO `mydatabase`.`items` (`name`, `path`, `url`, `category_id`, `user_id`) VALUES ('Test', 'Test', 'Test', '2', '1');
 