DROP database if exists  loginServlet;
create database loginServlet default charset 'utf8' COLLATE 'utf8_general_ci';
create table loginServlet.user(
id varchar(32),
name varchar(32),
password varchar(32),
primary key(`id`)
);
insert into loginServlet.user(`id`,`name`,`password`) values("100","小马","123456");