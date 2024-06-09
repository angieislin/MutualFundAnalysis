# Getting Started

## Build Steps
### Set up mysql server
#### docker run --name mysql-server -e MYSQL_ROOT_PASSWORD=myrootpassword -e MYSQL_DATABASE=fund_db -e MYSQL_USER=myuser -e MYSQL_USERS_PASSWORD=mypassword -p 3306:3306 -d mysql
#### docker exec -it mysql-server mysqsl -uroot -pmyrootpassowrd
#### run ddl.sql
### Set up network
#### docker network create my-network;
#### docker network connect my-network mysql-server;
### Set up application
#### docker build -t my-spring-boot-app .;
#### docker run -d --name  my-spring-boot-app  -p 8080:8080 --network my-network my-spring-boot-app;
