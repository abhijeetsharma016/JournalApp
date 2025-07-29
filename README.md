# 📖 JournalApp

A modern, feature-rich journaling application built with Java and Spring Boot, designed to help users capture their thoughts, experiences, and memories in a secure and organized manner.

## 🌟 Features

- **User Authentication & Authorization**: Secure user registration and login system
- **Create & Manage Entries**: Write, edit, and delete journal entries with ease
- **MongoDB Integration**: Robust data persistence with MongoDB database
- **Search Functionality**: Find specific entries quickly and efficiently
- **Responsive Design**: Clean and intuitive user interface
- **Data Security**: Secure storage of personal journal entries
- **Logging System**: Comprehensive logging with Logback for monitoring and debugging

## 🛠️ Technology Stack

- **Backend**: Java, Spring Boot
- **Database**: MongoDB
- **Build Tool**: Maven
- **Logging**: Logback (SLF4J)
- **Version Control**: Git

## 📋 Prerequisites

Before running this application, make sure you have the following installed:

- **Java 11** or higher
- **Maven 3.6+**
- **MongoDB** (local installation or MongoDB Atlas)
- **Git**

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/abhijeetsharma016/JournalApp.git
cd JournalApp
```

### 2. Configure MongoDB

#### Option A: Local MongoDB
- Install MongoDB locally
- Start MongoDB service
- Create a database named `journalapp`

#### Option B: MongoDB Atlas
- Create a MongoDB Atlas account
- Set up a cluster
- Get your connection string

### 3. Application Configuration

Update the MongoDB connection details in your application properties:

```properties
# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/journalapp
# OR for MongoDB Atlas
# spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/journalapp
```

### 4. Build and Run

#### Using Maven Wrapper
```bash
# Build the project
./mvnw clean compile

# Run the application
./mvnw spring-boot:run
```

#### Using Maven (if installed globally)
```bash
# Build the project
mvn clean compile

# Run the application
mvn spring-boot:run
```

### 5. Access the Application

Once the application starts successfully, it will be available at:
```
http://localhost:8080
```

## 📁 Project Structure

```
JournalApp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/yourpackage/journalapp/
│   │   │       ├── controller/          # REST Controllers
│   │   │       ├── model/              # Entity/Model classes
│   │   │       ├── repository/         # Data repositories
│   │   │       ├── service/            # Business logic
│   │   │       └── JournalAppApplication.java
│   │   └── resources/
│   │       ├── application.properties  # App configuration
│   │       └── logback.xml            # Logging configuration
│   └── test/                          # Unit tests
├── logs/                              # Application logs
├── .mvn/wrapper/                      # Maven wrapper files
├── mvnw                              # Maven wrapper script (Unix)
├── mvnw.cmd                          # Maven wrapper script (Windows)
├── pom.xml                           # Maven dependencies
├── .gitignore                        # Git ignore rules
└── README.md                         # Project documentation
```

## 🔧 Configuration

### Application Properties
Key configuration options in `application.properties`:

```properties
# Server Configuration
server.port=8080

# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/journalapp
spring.data.mongodb.database=journalapp

# Logging Configuration
logging.level.com.yourpackage.journalapp=DEBUG
logging.file.name=logs/journalApp.log
```

### Logging
The application uses Logback for logging. Check the `logs/` directory for application logs, or view them in the console during development.

## 🧪 Testing

Run the test suite:

```bash
# Using Maven wrapper
./mvnw test

# Using Maven
mvn test
```

## 📊 API Endpoints

### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login
- `POST /api/auth/logout` - User logout

### Journal Entries
- `GET /api/entries` - Get all entries for authenticated user
- `POST /api/entries` - Create a new journal entry
- `GET /api/entries/{id}` - Get specific entry by ID
- `PUT /api/entries/{id}` - Update an existing entry
- `DELETE /api/entries/{id}` - Delete an entry

### User Management
- `GET /api/users/profile` - Get user profile
- `PUT /api/users/profile` - Update user profile

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 🐛 Issues and Support

If you encounter any issues or have questions:

1. Check the [Issues](https://github.com/abhijeetsharma016/JournalApp/issues) tab
2. Create a new issue if your problem isn't already reported
3. Provide detailed information about the issue and steps to reproduce

## 👨‍💻 Author

**Abhijeet Sharma**
- GitHub: [@abhijeetsharma016](https://github.com/abhijeetsharma016)
- Email: [Your Email]

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- MongoDB team for the robust database solution
- Open source community for inspiration and resources

---

⭐ **Star this repository if you found it helpful!**

## 🔄 Recent Updates

- ✅ Added MongoDB integration
- ✅ Implemented logging with Logback
- ✅ Enhanced database configuration
- ✅ User entry management functionality

---

**Happy Journaling! 📖✨**
