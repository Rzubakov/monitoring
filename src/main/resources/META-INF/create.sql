INSERT INTO Profiles (`company`, `firstname`, `lastname`, `phone`) VALUES ('Home', 'Roman', 'Zubakov', '89857699366');
INSERT INTO Profiles (`company`, `firstname`, `lastname`, `phone`) VALUES ('Home', 'Roman', 'Zubakov', '89857699366');
INSERT INTO Users (`email`, `password`, `role`, `profile_id`) VALUES ('rzubakov@protonmail.com', '/gnplWM1A3X6t7sLuLjnOnqLZjAYewyEJLoq8+H6I4s=', 'ADMIN', '1');
INSERT INTO Users (`email`, `password`, `role`, `profile_id`) VALUES ('robot@bot', '/gnplWM1A3X6t7sLuLjnOnqLZjAYewyEJLoq8+H6I4s=', 'BOT','2');
INSERT INTO Categories (`description`, `name`) VALUES ('GlobalRoot', 'GlobalRoot');
INSERT INTO Categories (`description`, `name`, `parent_id`, `user_id`) VALUES ('Root', 'Root', '1', '1');
INSERT INTO Items (`name`, `path`, `url`, `category_id`, `user_id`) VALUES ('Test', 'Test', 'Test', '2', '1');
 