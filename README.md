# Market-app
_____________________________
This simple web application imitates the simple market work.

Project structure
-----------
Application is designed according to SOLID, REST principles, using DI and N-Tier Architecture patterns with next layers:
1. controllers layer;
2. services layer;
3. repositories layer;

Features
-----------
1. Create/update/delete user
2. Display all users
3. Display user by id
4. Update user after buying product
5. Display all users, that bought product, by product id
6. Create/update/delete product
7. Display all products
8. Display product by id
9. Display all products of user by user's id


Technologies
-----------
* Java 11
* Spring Boot
* Apache Maven
* Swagger
* MySQL

Usage
-----------
1. Install IntelliJ IDEA ultimate version;
2. Clone this project from GitHub and make sure that an absolute path doesn't include any white spaces and/or non-latin
   symbols;
4. Install MySQL and MySQL Workbench;
6. Configure application.properties file to make a connection to you DB;
7. Run application;
8. Test application using postman and/or your browser address bar

List of allowed http methods with available endpoints
-----------
```
POST: /users
PUT: /users/{id}
GET: /users
GET: /users/{id}
DELETE: /users/{id}
PUT: /users/{id}/buy
GET: /users/products/{id}
POST: /products
PUT: /products/{id}
GET: /products
GET: /products/{id}
DELETE: /products/{id}
GET: /products/users/{id}
```
_____________________________
* All incoming and outgoing data converted to JSON format.

