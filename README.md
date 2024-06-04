**Tools and technologies used**
JSP - 2.3
IDE - NetBeans IDE 19
JDK - 18 or later
Apache Tomcat - 9.0
JSTL - 1.2.1
Servlet API
MySQL - mysql-connector-java-8.2.0.jar


**Create a table using SQL syntax, this table must exist for the application to function**
create table users(
	id INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL,
    userPassword VARCHAR(50) NOT NULL
);
_TO INSERT INTO TABLE:_
INSERT INTO users (userName, email, userPassword) VALUES (101,'youremail@@email.com', 'password123');


Perform direct lookup of the DataSource using the JNDI name defined in [context.xml]

_**THIS PROJECT DOES NOT CONTAIN ANY CSS**_
