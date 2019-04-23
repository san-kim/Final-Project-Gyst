DROP DATABASE IF EXISTS Gyst;
CREATE DATABASE Gyst;
USE Gyst;

CREATE TABLE UserInfo (
	User_ID INT(11) PRIMARY KEY auto_increment NOT NULL,
    Username VARCHAR(50) NOT NULL,
    User_Password  VARCHAR(50) NOT NULL
);


CREATE TABLE User_Events (
	Event_ID INT(11) NOT NULL primary key auto_increment,
	User_ID INT(11) NOT NULL,
    Event_name VARCHAR(50) NOT NULL,
    location  VARCHAR(50) NULL,
    Start_time VARCHAR(50) NOT NULL,
    End_time VARCHAR(50) NOT NULL,
    notes VARCHAR(50) NULL,
    Host_ID INT(11) NOT NULL,
    
    FOREIGN KEY(User_ID) REFERENCES UserInfo(User_ID)
);

CREATE TABLE ToDoEvents (
	ToDoEvent_ID INT(11) PRIMARY KEY auto_increment NOT NULL,
	User_ID INT(11) NOT NULL,
    Event_name VARCHAR(50) NOT NULL,
    location  VARCHAR(50) NULL,
    Start_time VARCHAR(50) NOT NULL,
    End_time VARCHAR(50) NOT NULL,
    User_block BOOLEAN NOT NULL,
    notes VARCHAR(50) NULL,
    
    FOREIGN KEY(User_ID) REFERENCES UserInfo(User_ID)
);

