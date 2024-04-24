# java_kirjasta
Java backend training

## Get started
Create project with Spring Initializr `https://start.spring.io`
- Maven project
- Java
- Latest Spring Boot version
- Group field: define group ID (also becomes the base package in our Java project)
- Artifact: artifact ID (also name of our project)
- Add dependencies: `Spring Boot DevTools` and `Spring Web`
Generate Project. And save the zip in your project folder and Extract zip

## Running MariaDB in AWS EC2 Windows Instance
- MariaDB url = `mariadb://_INSTANCE_PUBLIC_IPV4:3306/_DATABASE_NAME_`
- Allow 3306 for the instance (inbound rule)
- Run this in the MariaDB in the intance `GRANT ALL PRIVILEGES ON *.* TO '_MariaDB_username_'@'_mac_public_ip_' IDENTIFIED BY '_MariaDB_password_' WITH GRANT OPTION;`


## Spring Security
https://spring.io/projects/spring-security


