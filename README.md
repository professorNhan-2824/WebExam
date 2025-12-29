# WebExam - Online Examination Platform

## Overview
WebExam is a Spring Boot-based web application that enables users to create, manage, and take online examinations. The platform provides a comprehensive solution for educational testing with intuitive user interfaces and robust backend architecture.

## Features

### Client-Side Features

#### Completed Features ✓
1. **Create Examination** - Users can create new exams with custom titles and descriptions
2. **Create Questions** - Add questions to exams with multiple choice, true/false, or other question types
3. **View Exam List** - Display all created exams with their details on the dashboard

#### Planned Features (In Development)
4. **Edit/Delete Exams** - Modify exam details or remove exams from the system
5. **Take Exam** - Allow users to attempt exams and submit their answers
6. **Search Exams** - Search and filter exams by title, category, or other criteria

### Server-Side Features
- RESTful API endpoints for exam and question management
- User authentication and authorization (Spring Security configured)
- Database persistence for exams, questions, and user answers
- Session management for exam-taking functionality

## Technology Stack
- **Framework**: Spring Boot
- **Build Tool**: Maven
- **Frontend**: HTML5, CSS3, JavaScript, Bootstrap
- **Security**: Spring Security
- **Database**: (Configured in application.yml)

## Project Structure
- `src/main/java/com/webquizz/` - Main application code
  - `config/` - Configuration classes (Spring Security)
  - `Controler/` - REST controllers for handling requests
  - `model/` - Entity models and data structures
  - `Reponsitory/` - Data access layer
  - `Service/` - Business logic layer
  - `session/` - Session management
  - `Request/` - Request DTOs
- `src/main/resources/` - Application resources
  - `templates/` - Thymeleaf HTML templates
  - `static/` - CSS and JavaScript assets

## Getting Started
1. Build the project: `mvn clean install`
2. Run the application: `mvn spring-boot:run` or `./mvnw spring-boot:run`
3. Access the application at `http://localhost:8080`
