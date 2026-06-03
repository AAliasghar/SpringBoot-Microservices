# SpringBoot Microservices Architecture

A comprehensive Spring Boot microservices project demonstrating a complete distributed system architecture with multiple independent services, API Gateway, service discovery, and configuration management.

## 📋 Project Overview

This project showcases a modern microservices architecture built with Spring Boot and Spring Cloud. It implements an employee management system across multiple domains (Departments, Employees, and Organizations) with centralized API Gateway routing, Eureka service discovery, and distributed configuration.

## 🏗️ Architecture Components

### **1. API Gateway**
- **Port:** 8765 (Default)
- **Technology:** Spring Cloud Gateway
- **Purpose:** Single entry point for all client requests, routing to appropriate microservices
- **Dependencies:** Spring Cloud Gateway, Eureka Client, Actuator

### **2. Service Registry (Eureka Server)**
- **Port:** 8761 (Default)
- **Technology:** Netflix Eureka
- **Purpose:** Service discovery and registration
- **Features:**
  - Automatic service registration/deregistration
  - Health monitoring
  - Load balancing

### **3. Employee Service**
- **Port:** 8081
- **Database:** MySQL (employee_db)
- **Features:**
  - Employee CRUD operations
  - Department service integration via Feign Client
  - Spring Data JPA for ORM
  - WebFlux support for reactive endpoints
  - Resilience4j circuit breaker pattern
  - Config Server integration
  - Spring Cloud Bus for configuration refresh

### **4. Department Service**
- **Port:** 8080
- **Database:** MySQL (department_db)
- **Features:**
  - Department CRUD operations
  - Centralized management of departments
  - Hibernate auto-DDL enabled

### **5. Organization Service**
- **Port:** 8083
- **Database:** MySQL (organization_db)
- **Features:**
  - Organization management
  - Integration with other services

### **6. Config Server**
- **Port:** 8888 (Default)
- **Purpose:** Centralized external configuration management
- **Features:**
  - Dynamic configuration updates via Spring Cloud Bus
  - Environment-specific property files

## 🗄️ Database Setup

The project uses MySQL with three separate databases:

```sql
-- Database schema is initialized via DB.sql
-- Databases created:
- department_db  (for Department Service)
- employee_db    (for Employee Service)
- organization_db (for Organization Service)
```

### Prerequisites

```bash
# Install MySQL Server
# Create databases using DB.sql script
mysql -u root -p < DB.sql
```

## 🚀 Getting Started

### Prerequisites

- **Java 17+**
- **Maven 3.6+**
- **MySQL 8.0+**
- **RabbitMQ** (for Spring Cloud Bus)

### Installation Steps

1. **Clone the repository:**
   ```bash
   git clone https://github.com/AAliasghar/SpringBoot-Microservices.git
   cd SpringBoot-Microservices
   ```

2. **Setup Database:**
   ```bash
   mysql -u root -p < DB.sql
   ```

3. **Update Configuration Files:**

   Edit the following property files with your MySQL credentials:

   - `department-service.properties`
   - `employee-service.properties`
   - `organization-service.properties`

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/[database_name]
   spring.datasource.username=root
   spring.datasource.password=root12345
   ```

4. **Start Services (in order):**

   **Terminal 1 - Service Registry:**
   ```bash
   cd service-registry/service-registry
   mvn spring-boot:run
   ```

   **Terminal 2 - Config Server:**
   ```bash
   cd config-server
   mvn spring-boot:run
   ```

   **Terminal 3 - API Gateway:**
   ```bash
   cd api-gateway/api-gateway
   mvn spring-boot:run
   ```

   **Terminal 4 - Employee Service:**
   ```bash
   cd employee-service/employee-service
   mvn spring-boot:run
   ```

   **Terminal 5 - Department Service:**
   ```bash
   cd department-service/department-service
   mvn spring-boot:run
   ```

   **Terminal 6 - Organization Service:**
   ```bash
   cd organization-service/organization-service
   mvn spring-boot:run
   ```

## 📡 API Endpoints

All requests go through the API Gateway at `http://localhost:8765/`

### Employee Service Endpoints
```
GET    /employee-service/employees
GET    /employee-service/employees/{id}
POST   /employee-service/employees
PUT    /employee-service/employees/{id}
DELETE /employee-service/employees/{id}
```

### Department Service Endpoints
```
GET    /department-service/departments
GET    /department-service/departments/{id}
POST   /department-service/departments
PUT    /department-service/departments/{id}
DELETE /department-service/departments/{id}
```

### Organization Service Endpoints
```
GET    /organization-service/org
GET    /organization-service/org/{id}
POST   /organization-service/org
PUT    /organization-service/org/{id}
DELETE /organization-service/org/{id}
```

## 🔍 Service Discovery

**Eureka Dashboard:** `http://localhost:8761/`

View all registered services and their health status in real-time.

## 🛠️ Key Technologies

| Component | Technology | Version |
|-----------|-----------|---------|
| Framework | Spring Boot | 3.0.6 - 3.0.7 |
| Cloud | Spring Cloud | 2022.0.2 |
| Service Discovery | Netflix Eureka | Latest |
| API Gateway | Spring Cloud Gateway | Latest |
| ORM | Spring Data JPA + Hibernate | Latest |
| Database | MySQL | 8.0+ |
| HTTP Client | OpenFeign | Latest |
| Resilience | Resilience4j | Latest |
| Config Management | Spring Cloud Config | Latest |
| Message Bus | RabbitMQ (Spring Cloud Bus) | Latest |
| Build Tool | Maven | 3.6+ |
| Language | Java | 17 |

## 📚 Project Structure

```
SpringBoot-Microservices/
├── api-gateway/
│   └── api-gateway/               # Spring Cloud Gateway
│       ├── pom.xml
│       └── src/
├── service-registry/
│   └── service-registry/          # Eureka Server
│       ├── pom.xml
│       └── src/
├── employee-service/
│   └── employee-service/          # Employee Microservice
│       ├── pom.xml
│       └── src/
├── department-service/
│   └── department-service/        # Department Microservice
│       ├── pom.xml
│       └── src/
├── organization-service/
│   └── organization-service/      # Organization Microservice
│       ├── pom.xml
│       └── src/
├── config-server/                 # Config Server
│       └── src/
├── react-frontend/                # React Frontend (Optional)
├── DB.sql                          # Database initialization script
├── department-service.properties   # Department config
├── employee-service.properties     # Employee config
└── organization-service.properties # Organization config
```

## 🔄 Inter-service Communication

- **Employee Service** communicates with **Department Service** using **OpenFeign** client
- **Resilience4j** circuit breaker pattern prevents cascading failures
- **Service Registry** enables dynamic service discovery without hardcoded URLs

## ⚙️ Configuration Management

The project uses **Spring Cloud Config Server** with **Spring Cloud Bus** for:
- Centralized configuration
- Dynamic property updates without service restart
- Environment-specific configurations

## 🧪 Testing

Run tests for individual services:

```bash
# Test Employee Service
cd employee-service/employee-service
mvn test

# Test Department Service
cd department-service/department-service
mvn test
```

## 📊 Monitoring & Actuator

Each service includes Spring Boot Actuator for monitoring:
- Health checks: `http://localhost:[port]/actuator/health`
- Metrics: `http://localhost:[port]/actuator/metrics`
- Endpoints: `http://localhost:[port]/actuator`

## 🐛 Troubleshooting

### Services not registering with Eureka
- Ensure Service Registry is running on port 8761
- Check firewall settings
- Verify network connectivity

### Database connection errors
- Verify MySQL is running
- Check credentials in property files
- Ensure databases are created

### API Gateway routing issues
- Check Eureka dashboard to ensure services are registered
- Verify gateway route configurations
- Check service names in routing rules

### RabbitMQ connection (for Spring Cloud Bus)
- Ensure RabbitMQ is running
- Check RabbitMQ connection properties in config files

## 📝 Configuration Properties

### Database Configuration
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/[db_name]
spring.datasource.username=root
spring.datasource.password=root12345
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Eureka Configuration
```properties
eureka.instance.client.serverUrl.defaultZone=http://localhost:8761/eureka/
```

## 🚦 Development Workflow

1. **Start infrastructure** (Eureka, Config Server)
2. **Start business services** (Employee, Department, Organization)
3. **Start API Gateway** last
4. **Access services** through API Gateway

## 📄 License

This project is open source and available under the MIT License.

## 👤 Author

**Ali Asghar** - [GitHub Profile](https://github.com/AAliasghar)

## 🤝 Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues for bugs and feature requests.

## 📞 Support

For issues and questions, please open a GitHub issue in the repository.

---

**Last Updated:** June 2023  
**Project Created:** May 2023  
**Language Composition:** Java (89.7%), HTML (6.1%), CSS (3.3%), JavaScript (0.9%)
