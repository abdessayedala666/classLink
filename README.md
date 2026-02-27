# Classlink

Classlink is a modern school administrative management system designed to streamline and secure school operations. It features a robust backend built with Spring Boot and a dynamic frontend using Angular. The system enforces fine-grained authorization using reBAC (relationship-based access control) via SpiceDB (an implementation of the Zanzibar paper), ensures strong password policies with Passay, and supports file upload persistence with MinIO (S3-compatible). For development and testing, Maildev is used as a local SMTP server.

## Key Features & Technologies

### Backend (Spring Boot)
- **Spring Boot 4**: Rapid development with production-ready features.
- **Spring Security**: Custom security configuration, session management, and integration with reBAC.
- **SpiceDB (Authzed)**: Implements relationship-based access control for fine-grained permissions, inspired by Google Zanzibar.
- **Passay**: Enforces strong password rules (length, character types, no whitespace).
- **MySQL**: Reliable relational database for persistent storage.
- **Jakarta Validation**: Ensures data integrity and validation.
- **Maildev**: Local SMTP server for email testing.
- **Best Practices**: 
  - Modular service and validation layers.
  - Secure session and cookie management.
  - Test dependencies for robust backend testing.

### Frontend (Angular)
- **Angular 21**: Modern, scalable SPA framework.
- **NgRx**: State management using Redux pattern for predictable state and side-effect handling.
- **Prettier**: Enforced code formatting for maintainability.
- **TypeScript**: Type safety and modern JavaScript features.
- **Component-based architecture**: Organized by features and roles (admin, teacher, student, parent).
- **Best Practices**:
  - Modular folder structure.
  - Route guards for authentication and first-login flows.
  - Shared components for UI consistency.

### Storage & File Uploads
- **MinIO**: S3-compatible object storage for file uploads and persistence (planned/expected based on your description).

### Authorization & Security
- **reBAC with SpiceDB**: Fine-grained, relationship-based access control.
- **Strong password enforcement**: Passay-based validation.
- **Session security**: Secure, HTTP-only cookies, session timeouts.

### Development & Testing
- **Maildev**: Local SMTP server for safe email testing.
- **Spring Boot Devtools**: Hot reloading for rapid backend development.
- **Comprehensive test dependencies**: For both security and web layers.

---

## Getting Started

### Prerequisites
- Java 17+ (recommended for Spring Boot 4)
- Node.js 18+ and npm
- MySQL server
- MinIO server (for file uploads)
- Maildev (for email testing)
- SpiceDB server

### Backend Setup

```bash
cd back-end
# Configure application.properties for your environment
./mvnw spring-boot:run
```

### Frontend Setup

```bash
cd front-end
npm install
npm start
```

### SpiceDB, MinIO, and Maildev

- **SpiceDB**: [SpiceDB Documentation](https://authzed.com/docs/)
- **MinIO**: [MinIO Quickstart](https://min.io/docs/minio/linux/quickstart.html)
- **Maildev**: [Maildev GitHub](https://github.com/maildev/maildev)

---

## Notable Implementations

- **Custom SecurityConfig**: Fine-tuned session, CSRF, and authentication management.
- **SpiceDBAuthorizationService**: Centralized service for permission checks and relationship management.
- **StrongPasswordValidator**: Enforces complex password rules using Passay.
- **NgRx State Management**: Predictable, scalable state handling in Angular.
- **Prettier Formatting**: Consistent code style across the frontend.

---

## Contributing

Contributions are welcome! Please open issues or submit pull requests for improvements.

---

## License

This project is for educational and demonstration purposes.
