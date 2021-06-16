# 小小志的Java课设

# 目录结构

```c
src //根目录
|--- Controller //控制器
|   |--- AdminController.java
|   |--- Controller.java
|   |--- login.java
|   |--- UserController.java
| 
|--- DAO //数据访问层
|    |--- DAO.java
|    |--- PartsDAO.java
|    |--- UserDAO.java
| 
|--- Model //模型
|    |--- Admin.java
|    |--- Main.java
|    |--- Parts.Java
|    |--- Person.java
|    |--- User.java
| 
|--- View //界面
     |--- admin.css
     |--- admin.fxml
     |--- login.css
     |--- login.fxml
     |--- user.fxml
     |--- img
          |---- background.png
          |---- icon.png
```

# 数据库

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
PASSWORD varchar(16) not null,
IDENTITY bit(1) not null);
```