![](images/start.jpg)
<p align="center" style="font-size: 38px">
</p>

## Cinema-Service
### Project description:

This is an application that represents the Cinema service. It is built using SOLID principles and implemented such
functions as authentication, registration, selection of all movies, user tickets, don't think that you have such function. With the ability to add new ones and delete them
users, tickets and movies. Interaction with the database implemented using `Spring`.

## Features 👀️:

- Find user by email 
- Login and registration
- Create cinema hall, movie, movie session, order, shopping cart
- Get cinema halls, movies, orders

## Realization details
### The project uses the following architecture:
### Project based on 3-layer architecture:
- Data access layer (DAO)
- Application layer (services)
- REST layer (controllers)

## Technologies that were used to create the service:
### Technologies:
  - Apache Tomcat (to run app locally)
  - PostgreSQL
  - Hibernate
  - Maven
  - Log4j
  - Spring

### Tests:
  - Spring-test
  - JUnit 5
  - Mockito
  - HSQL
  - Postman

### [GitHub repository](https://github.com/DenysShl/cinema-service.git)

## 🚀️ Installation and launch project 🚀️

1. Type git clone, and then paste the URL you copied earlier.
  - `$ git clone https://github.com/DenysShl/cinema-service.git`
2. Create database in `PostgreSQL`
  ```sql
    CREATE DATABASE cinema
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Ukrainian_Ukraine.1251'
    LC_CTYPE = 'Ukrainian_Ukraine.1251'
    CONNECTION LIMIT = -1;
  ```
3. Configure `db.properties` that's located in resources folder.
4. Add TomCat version 9.0.63 to the project configuration.
5. Use `db.properties` to specify the user and password settings for the connection database
6. Specify the folder and path where the logs will be stored in the `log4j2.properties` file located in the `resources` folder
7. You can login on web pages: Login: `admin@i.ua` Password: `admin123`
8. You can check the registration through "Postman" using: 
   - method: `POST` 
   - url: `http://localhost:8088/register`
   - body:
```json
{
    "email":"alisa.shd@gmail.com",
    "password":"sdfaew3r!@&!",
    "repeatPassword":"sdfaew3r!@&!"
}

```
the answer should be in the form of:
```json
{
    "id": 2,
    "email": "alisa.shd@gmail.com"
}
```



## _Database structure_

![](images/structure_db_spring.png)