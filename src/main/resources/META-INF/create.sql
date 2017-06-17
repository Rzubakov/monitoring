INSERT INTO Users (`email`, `password`, `role`) VALUES ('rzubakov@protonmail.com', '/gnplWM1A3X6t7sLuLjnOnqLZjAYewyEJLoq8+H6I4s=', 'ADMIN');
INSERT INTO Users (`email`, `password`, `role`) VALUES ('robot@bot', '/gnplWM1A3X6t7sLuLjnOnqLZjAYewyEJLoq8+H6I4s=', 'BOT');
INSERT INTO Categories (`description`, `name`) VALUES ('GlobalRoot', 'GlobalRoot');
INSERT INTO Categories (`description`, `name`, `parent_id`, `user_id`) VALUES ('Root', 'Root', '1', '1');
INSERT INTO Items (`name`, `path`, `url`, `category_id`, `user_id`) VALUES ('Test', 'Test', 'Test', '2', '1');
 