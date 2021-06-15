# computerPartsMVC
Java课设
数据库

创建数据库
```mysql
create database partsdata;
```
连接数据库
```mysql
use partsdata;
```
库存表
```mysql 
create table parts(
ID int not null primary key auto_increment,
NAME varchar(20) not null,
UNIT_PRISE int not null,
AMOUNT int not null,
SORT varchar(255) not null);
```
用户表
```mysql
create table user(
USERNAME varchar(20) not null primary key auto_increment,
PASSWORD vharchar(16) not null,
IDENTITY bit(1) not null);
```
