drop database if exists servlet_blog;

create database servlet_blog character set utf8mb4;

use servlet_blog;
create table user(
id int primary key auto_increment,
username varchar(20) not null unique comment '用户名字',
password varchar(20) not null,
nickname varchar(20),
sex bit,
birthday date,
head varchar(50)


);


create table article(
id int primary key auto_increment,
title varchar(20) not null,
content mediumtext not null,
create_time timestamp default now(),
view_count int default 0,
user_id int,
foreign key (user_id) references user(id)

);


insert into user(username,password) values('a','123');
insert into user(username,password) values('b','456');
insert into user(username,password) values('c','789');

insert into article(title, content,user_id) values ('快速排序','public ***',1);
insert into article(title, content,user_id) values ('冒泡排序','public ***',2);
insert into article(title, content,user_id) values ('希尔排序','public ***',2);
insert into article(title, content,user_id) values ('堆排序','public ***',1);


--主外键关联的表，默认的主外键约束是restrict严格模式，
-- 比如从表有一行数据关联到主表，那么这一行数据就不能删除
--真实的设计上是使用不删除物理数据，在每一张表上设计一个字段，表示是否有效

select username,password,nickname,sex,birthday,head from user where username=?


select id,title from article user_id=?






