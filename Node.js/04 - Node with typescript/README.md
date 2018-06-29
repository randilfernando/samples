# Node.js + Typescript
- This is for you if you want to develop node applications with typescript  
-  This is for you if you are migrating from statically typed languages and love to use types with node.js  

## Project architecture
This project use a similar folder structure which you can see in frameworks like [spring](https://spring.io/)  
1. entities - all the database entities and their mappings
2. every module contains controllers and services
3. controllers use service methods to execute logic and containers only contain request and response mappings
4. services use repositories provided by the ORM
5. [typeorm](http://typeorm.io/#/) provides a convenient way of using annotations to initialize database mappings

## How to run
run ```npm run start``` to start application server

## Test
1. [GET] /users - get all users
2. [GET] /users/:id - get user with id
3. [POST] /users - add new users
4. [PUT] /users/:id - update user with id