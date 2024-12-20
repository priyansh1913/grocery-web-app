# Grocery Web Application

A Spring Boot based web application for managing grocery items with user authentication.

## Features (In Development)

- User Authentication (Login/Register)
- Product Management
- Shopping Cart
- Order Processing
- User Profile Management

## Tech Stack

- Java 11
- Spring Boot 2.7.0
- Spring Security
- Spring Data JPA
- MySQL Database
- Thymeleaf
- Maven

## Branch Structure

- `main` - Production ready code
- `develop` - Development branch
- `feature/user-auth` - User authentication feature
- `feature/product-management` - Product management feature
- `feature/shopping-cart` - Shopping cart feature
- `feature/order-processing` - Order processing feature

## Setup Instructions

1. Clone the repository
2. Configure MySQL database
3. Update `application.properties` with your database credentials
4. Run `mvn clean install`
5. Run the application using `mvn spring-boot:run`

## Database Configuration

Create a MySQL database and update the following properties in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/grocery_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Contributing

1. Create a new feature branch from `develop`
2. Make your changes
3. Submit a pull request to merge back into `develop`
