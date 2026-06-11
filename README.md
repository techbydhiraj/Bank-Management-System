# Bank Management System

## Features
- Customer CRUD
- Account CRUD
- Customer-Account Relationship
- Transaction CRUD
- transaction-Account Relationship

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

#How to run program 
1. Run Spring boot application loader file
2. once successfully compiled without Error we ready to use

#Database table relationship 
Customer<->One-to-many ----> Accounts
Account<-> Many-to-one ----> Customers
Transaction<->Many-to-Many ----> Accounts
