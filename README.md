# Ekstraklasa-Stats-with-JDBC
Project of displaying data about Polish Footbal League - Ekstraklasa from database by simple GUI application. 

## Table of contents
* [Introduction](#introduction)
* [Technologies](#technologies)
* [Setup](#setup)
* [Screenshots](#screenshots)


## Introduction
Ekstraklasa-Stats-with-JDBC is the Maven project made for better communication between user and database. Project is dividing into data, application and presentation layers. 
Thanks to JDBC interface and Data Access Objects program is connecting with database by specific queries. Application layer allows to create suitable
objects from received data. Presentation layer classes display results on screen.

## Technologies
To create this project is used:
* Maven,
* Java 16,
* JDBC interface,
* SQL,
* Swing library.

## Setup
For communication between Java application and database is necessary to install JDBC driver dedicated to database server. In this case JDBC for [SQL Server](https://docs.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15) is used.

To create the right database about Ekstraklasa follow: [Ekstraklasa-Database](https://github.com/DariuszRKozlowski/Ekstraklasa-Database).

For successfully connection between application and database is necessary to configure `database.properties` file by own data about path, username and password.

## Screenshots

| ![](https://github.com/DariuszRKozlowski/images/blob/main/x1.PNG) |
|:--:|
| <b>Main menu</b>|

| ![](https://github.com/DariuszRKozlowski/images/blob/main/2x.PNG) |
|:--:|
| <b>Club submenu</b>|

| ![](https://github.com/DariuszRKozlowski/images/blob/main/3x.PNG) |
|:--:|
| <b>Display club players input dialog</b>|

| ![](https://github.com/DariuszRKozlowski/images/blob/main/4x.PNG) |
|:--:|
| <b>Result</b>|
