# 📚 Bibliothèque — Library Management API

A Restful **Library Management System** built with **Spring Boot**, **Spring Data JPA**, **Hibernate**, **H2**, **MapStruct**, and **Lombok**.

The application allows you to manage users, authors, categories, books, and book loans through a REST API.

## 🚀 Features

* 👤 User management
* 🔐 User roles:

    * `ADMIN`
    * `LIBRARIAN`
    * `MEMBER`
* ✍️ Author management
* 📂 Book category management
* 📖 Book management
* 📦 Book quantity/availability management
* 🔄 Borrow and return books
* 📅 Automatic loan and due dates
* 🗃️ Loan history
* 🔁 DTO mapping with MapStruct
* 💾 H2 database for development
* 🌐 RESTful API

## 🛠️ Technologies

| Technology      | Purpose               |
| --------------- | --------------------- |
| Java            | Programming language  |
| Spring Boot     | Backend framework     |
| Spring Web      | REST API              |
| Spring Data JPA | Database access       |
| Hibernate       | ORM                   |
| H2 Database     | Development database  |
| MapStruct       | Entity ↔ DTO mapping  |
| Lombok          | Boilerplate reduction |
| Maven           | Dependency management |



## ⚙️ Requirements

Before running the project, make sure you have installed:

* Java 17+
* Maven 3.9+
* Git

## ▶️ Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/rihab4/YOUR_REPOSITORY.git
cd YOUR_REPOSITORY
```

### 2. Build the project

```bash
mvn clean install
```

### 3. Run the application

```bash
mvn spring-boot:run
```

The API will be available at:

```text
http://localhost:8081
```

## 🗄️ Database

The application currently uses **H2** as the database for development.

Depending on your configuration, you can access the H2 console at:

```text
http://localhost:8081/h2-console
```

Example configuration:

```properties
spring.datasource.url=jdbc:h2:mem:bibliotheque
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> For production, a persistent database such as PostgreSQL or MySQL should be used instead of an in-memory H2 database.

## 🔗 API Endpoints

### 👤 Users

#### Register a user

```http
POST /api/v1/users/register
```

Example request:

```json
{
  "firstName": "Rihab",
  "lastName": "Hsairi",
  "email": "rihab@example.com",
  "password": "password123",
  "telephone": "0614663090",
  "address": "Puteaux",
  "role": "MEMBER"
}
```

Available roles:

```text
ADMIN
LIBRARIAN
MEMBER
```

---

### ✍️ Authors

Create an author:

```http
POST /api/v1/authors
```

Example:

```json
{
  "firstName": "J.R.R.",
  "lastName": "Tolkien",
  "dateOfBirth": "1892-01-03"
}
```

---

### 📂 Categories

Create a category:

```http
POST /api/v1/categories
```

Example:

```json
{
  "name": "Fantasy",
  "description": "Books featuring magical worlds, mythical creatures, supernatural elements, and imaginative adventures."
}
```

---

### 📖 Books

Create a book:

```http
POST /api/v1/books/add
```

Example:

```json
{
  "title": "The Hobbit",
  "isbn": "9780261102217",
  "publicationDate": "1937-09-21",
  "pageNumber": 310,
  "quantity": 3,
  "authorId": 2,
  "categoryId": 1
}
```

The `authorId` and `categoryId` must correspond to existing records.

---

## 🔄 Loans

### Borrow a book

```http
POST /api/v1/loan/borrow
```

Request:

```json
{
  "userId": 1,
  "bookId": 2
}
```

When a book is borrowed:

* The book quantity is decreased by `1`
* The loan date is set automatically
* The due date is set to **14 days** after the loan date
* The loan status becomes `BORROWED`

Example response:

```json
{
  "id": 1,
  "loanDate": "2026-09-03",
  "dueDate": "2026-09-17",
  "returnDate": null,
  "status": "BORROWED",
  "bookId": 2,
  "userId": 1
}
```

### Return a book

```http
PUT /api/v1/loan/{loanId}/return
```

Example:

```http
PUT /api/v1/loan/1/return
```

When the book is returned:

* `returnDate` is set automatically
* Loan status becomes `RETURNED`
* Book quantity is increased by `1`

### Get all loans

```http
GET /api/v1/loan/getAllLoans
```

## 📊 Loan Status

Loans can have the following statuses:

```text
BORROWED
RETURNED
```

## 🧪 Testing

You can test the API using tools such as:

* Postman
* Insomnia
* cURL
* Swagger/OpenAPI, if configured

### Example test flow

```text
1. Create an author
       ↓
2. Create a category
       ↓
3. Create a book
       ↓
4. Register a user
       ↓
5. Borrow the book
       ↓
6. Check the loan
       ↓
7. Return the book
       ↓
8. Check the updated quantity
```

## 🔮 Future Improvements

Possible improvements for the project:

* [ ] Authentication with Spring Security
* [ ] JWT authentication
* [ ] Role-based authorization
* [ ] Password hashing with BCrypt
* [ ] Global exception handling with `@ControllerAdvice`
* [ ] Bean Validation (`@Valid`, `@NotNull`, `@Email`, etc.)
* [ ] Swagger/OpenAPI documentation
* [ ] Pagination and sorting
* [ ] Search books by title, author, or ISBN
* [ ] Loan history per user
* [ ] Overdue loan detection
* [ ] Email notifications for overdue books
* [ ] PostgreSQL/MySQL integration
* 
* [ ] Unit and integration tests
* [ ] Docker support

## 👩‍💻 Author

**Rihab Hassairi**

Built as a Spring Boot REST API project for managing a library.

## 📄 License

This project is currently intended for educational and development purposes.
