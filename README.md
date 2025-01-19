# Employee Management System

A Spring Boot-based RESTful API for managing employees within an organization. This application supports CRUD operations, searching, and filtering of employee records, utilizing Spring Data JPA, Hibernate, and a relational database.

## Technologies
- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate
- Docker
- Oracle Database (or H2 for development)
- OpenAPI Specification (Swagger UI)

## Getting Started

### Prerequisites
- **Java 17**
- **Docker**
- **Maven**

### Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/mfirass/employee-management-system.git
   cd employee-management-system
2. Build the project:
   ```bash
   mvn clean package
3. Run the application with Docker:
   ```bash
   docker build -t employee-management-system .
   docker run -p 8080:8080 employee-management-system
4. Access the API:
   - Visit http://localhost:8080/api/v1/employees for employee endpoints.

### API Endpoints

| Method | Endpoint                        | Description                                   |
|--------|----------------------------------|-----------------------------------------------|
| GET    | `/api/v1/employees`              | Retrieve all employees                        |
| GET    | `/api/v1/employees/{id}`         | Retrieve an employee by ID                    |
| POST   | `/api/v1/employees`              | Create a new employee                         |
| PUT    | `/api/v1/employees/{id}`         | Update an existing employee                   |
| DELETE | `/api/v1/employees/{id}`         | Delete an employee                            |
| GET    | `/api/v1/employees/search`       | Search for employees with various filters     |

### Users (For testing purposes)

| Email              | Role              | Department |
|--------------------|------------------|------------|
| admin@company.com  | ADMINISTRATOR     | N/A        |
| hr@company.com     | HR_PERSONNEL      | N/A        |
| manager@company.com | MANAGER           | IT         |