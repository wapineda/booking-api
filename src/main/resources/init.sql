DROP TABLE IF EXISTS `BOOKINGS`;
DROP TABLE IF EXISTS `USERS`;

CREATE TABLE `USERS` (
     `id` INT NOT NULL PRIMARY KEY auto_increment,
     `name` VARCHAR(255) NOT NULL,
     `last_name` VARCHAR(255),
     `birthday` DATE,
     `email` VARCHAR(255),
     `cellphone_number` VARCHAR(255)
);

CREATE TABLE `BOOKINGS` (
  `id` INT NOT NULL PRIMARY KEY auto_increment,
  `user_id` INT NOT NULL,
  `date_from` DATE NOT NULL,
  `date_to` DATE NOT NULL,
  `cancelled` BIT,
  FOREIGN KEY (user_id) references USERS(id)
);
