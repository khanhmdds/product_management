use product_management;
-- create database product_management;

-- create table admin (
--     username varchar(120) primary key,
--     password varchar(120) not null
-- );

-- insert into admin(username, password) values ('admin', 'password');

-- create table user (
-- 	id INT AUTO_INCREMENT PRIMARY KEY,
--     username varchar(120),
--     password varchar(120),
--     phoneNumber varchar(120),
--     email varchar(120),
--     address varchar(120)
-- );

-- insert into user(username, password, phoneNumber, email) values
-- 	('khanhmd.ds','password','0862442712', 'khanhmd.ds@gmail.com'), 
--     ('khanhmd.dev','password','0399444683', 'khanhmd.dev@gmail.com');

-- UPDATE user
-- SET address = 'Hue'
-- WHERE id = 2;
-- select * from user;

-- create table category (
-- 	id INT AUTO_INCREMENT PRIMARY KEY,
--     name varchar(120)
-- );

-- insert into category(id, name) values
--     (1, 'Computer'),
--     (2, 'Keyboard'),
--     (3, 'Mouse'),
--     (4, 'Laptop');

-- create table product (
-- 	id INT AUTO_INCREMENT PRIMARY KEY,
--     title varchar(120),
--     image varchar(120),
--     price int,
--     quantity int,
--     description varchar(120),
--     idcategory int,
--     FOREIGN KEY (idcategory) REFERENCES category(id)
-- );

-- create table orderProduct (
-- 	id_user int,
--     id_product int,
--     dateOrder datetime,
--     FOREIGN KEY (id_user) REFERENCES user(id),
--     FOREIGN KEY (id_product) REFERENCES product(id)
-- );
-- alter table orderProduct add quantityOder int;