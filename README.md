# 🏦 Online Bank Management System

A secure and scalable Online Bank Management System built using Java, Spring Boot, MySQL, and REST APIs. The project simulates real-world banking operations such as account management, fund transfers, transaction history, and user authentication.

---

## 🚀 Features

- Create Bank Account
- Deposit Money
- Withdraw Money
- Fund Transfer
- Check Account Balance
- Transaction History
- Account Details
- RESTful APIs
- Database Integration

---

## 🛠 Tech Stack

### Backend
- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate

### Database
- MySQL

### API Testing
- Postman

### Version Control
- Git
- GitHub
- 
## 📂 Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
```

## 📌 Modules
### Account Management
- Create Account
- View Account
- Update Account
- Delete Account

### Transactions
- Deposit
- Withdraw
- Fund Transfer
- Transaction History
- 
## ⚙️ Installation

### Clone Repository

```bash
git clone https://github.com/techbydhiraj/online-bank-management-system.git
```

### Navigate

```bash
cd online-bank-management-system
```

### Configure Database

Create a MySQL database.

Update:

```
application.properties
```

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```

### Run

```bash
mvn spring-boot:run
```

---

## 📬 API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | /register | Register User |
| POST | /login | Login |
| POST | /accounts | Create Account |
| GET | /accounts/{id} | Get Account |
| POST | /deposit | Deposit Money |
| POST | /withdraw | Withdraw Money |
| POST | /transfer | Transfer Funds |
| GET | /transactions | Transaction History |

---

## 🧪 Tested Using

- Postman

## Future Improvements

- JWT Authentication
- Email Notifications
- SMS Alerts
- Admin Dashboard
- Role-Based Authorization
- Docker
- Unit Testing
- CI/CD Pipeline

## 📷 Screenshots

> Add screenshots of:
- Login
- Dashboard
- Account Details
- Transaction History
- Postman API Testing

---
## 👨‍💻 Author

**Dhiraj Birajdar**

GitHub: https://github.com/techbydhiraj

---

## ⭐ If you found this project useful, give it a star!
