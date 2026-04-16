# 📚 Bookzilla — Reading Books Tracker

A full-stack **Reading Books Tracker** web application built with **Spring Boot MVC**, **Thymeleaf**, **Spring Security**, and an **H2 in-memory database**. The system enables users to manage personal reading lists, track reading progress across books, and allows admins to manage the content library.

---

## 🚀 Tech Stack

| Component       | Technology                          |
|-----------------|-------------------------------------|
| Language        | Java 17                             |
| Framework       | Spring Boot 3.2.5                   |
| View Layer      | Thymeleaf (Server-Side Rendering)   |
| Architecture    | Spring MVC (Model-View-Controller)  |
| Security        | Spring Security 6 (Form Login, RBAC)|
| Database        | H2 In-Memory Embedded Database      |
| ORM             | Spring Data JPA / Hibernate         |
| Build Tool      | Maven                               |
| Utilities       | Lombok                              |

---

## ✨ Features

### User (Reader)
- Register and log in with email and password
- Browse the full book catalog (Fiction, Programming, Productivity)
- Create personal named **Reading Lists** and add books to them
- Start tracking any book and update progress by **percentage** or **pages read**
- View a personal **dashboard** with all active reading progress and lists

### Admin
- Add, edit, and delete books in the content library
- View all registered users and the books they are currently reading
- Access admin dashboard for platform-wide oversight

---

## 🧩 Design Patterns Implemented

| Pattern      | Category    | File(s)                                           |
|--------------|-------------|---------------------------------------------------|
| Factory      | Creational  | `pattern/factory/ContentFactory.java`             |
| Singleton    | Creational  | `config/AppSettingsManager.java`                  |
| Builder      | Creational  | `pattern/builder/ReadingListBuilder.java`         |
| Prototype    | Creational  | `pattern/prototype/ProgressTemplate.java`         |
| Facade       | Structural  | `pattern/facade/UserDashboardFacade.java`         |

---

## 🏛️ SOLID Principles Applied

| Principle                        | How Applied                                                                                     |
|----------------------------------|-------------------------------------------------------------------------------------------------|
| Single Responsibility (SRP)      | Each service handles one domain entity; each controller handles one route group                 |
| Open-Closed (OCP)                | New book genres can be added as subclasses without modifying existing factory or service logic   |
| Liskov Substitution (LSP)        | `FictionBook`, `ProgrammingBook`, `ProductivityBook` are fully substitutable for `Content`      |
| Dependency Inversion (DIP)       | `DashboardController` depends only on `UserDashboardFacade` abstraction, not concrete services  |

---

## 📁 Project Structure

```
com.tracker
├── config/
│   ├── AppSettingsManager.java      ← Singleton Pattern
│   └── SecurityConfig.java
├── controller/
│   ├── DashboardController.java     ← Uses Facade Pattern
│   ├── AdminController.java
│   ├── AuthController.java
│   ├── ContentController.java
│   ├── ReadingListController.java
│   └── ReadingProgressController.java
├── model/
│   ├── Content.java                 ← Abstract base (Single Table Inheritance)
│   ├── Book.java                    ← Abstract intermediate
│   ├── FictionBook.java
│   ├── ProgrammingBook.java
│   ├── ProductivityBook.java
│   ├── ReadingList.java
│   ├── ReadingProgress.java         ← Implements Cloneable
│   ├── User.java
│   └── enums: Genre, Role, ReadingStatus
├── pattern/
│   ├── builder/ReadingListBuilder.java    ← Builder Pattern
│   ├── facade/UserDashboardFacade.java    ← Facade Pattern
│   ├── facade/DashboardData.java          ← Facade DTO
│   ├── factory/ContentFactory.java        ← Factory Pattern
│   └── prototype/ProgressTemplate.java    ← Prototype Pattern
├── repository/
│   ├── UserRepository.java
│   ├── ContentRepository.java
│   ├── ReadingListRepository.java
│   └── ReadingProgressRepository.java
└── service/
    ├── UserService.java
    ├── ContentService.java
    ├── ReadingListService.java
    ├── ReadingProgressService.java
    └── UserDetailsServiceImpl.java
```

---

## 🗃️ Database Schema

| Table                  | Key Columns                                                        |
|------------------------|--------------------------------------------------------------------|
| `users`                | `user_id`, `name`, `email`, `password`, `role`                     |
| `content`              | `content_id`, `title`, `author`, `description`, `genre`, `total_pages` |
| `reading_list`         | `list_id`, `name`, `user_id`                                       |
| `reading_list_content` | `list_id`, `content_id` (many-to-many join)                        |
| `reading_progress`     | `progress_id`, `user_id`, `content_id`, `status`, `percentage`, `pages_read` |

---

## ▶️ Running the Application

### Prerequisites
- Java 17+
- Maven 3.8+

### Steps

```bash
# Clone the repository
git clone https://github.com/Notshreyasrbhat/Bookzilla.git
cd Bookzilla

# Run the application
mvn spring-boot:run
```

Then open your browser and navigate to:
```
http://localhost:8080
```

### Default Credentials (seeded via `data.sql`)

| Role  | Email               | Password  |
|-------|---------------------|-----------|
| Admin | admin@tracker.com   | admin |

### H2 Console (Development)
```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:trackerdb
```

---

## 👥 Team Contributions

### 🔵 Shreyas Bhat — Project Lead & Core Architecture

- **Project Setup**: Initialized Spring Boot project, configured Maven (`pom.xml`), set up application properties and H2 database seeding (`data.sql`)
- **Spring Security**: Implemented full role-based authentication — login, registration, logout, route-level access control (`SecurityConfig.java`, `AuthController.java`, `UserDetailsServiceImpl.java`)
- **Domain Model Design**: Designed the entire entity hierarchy — `Content` (abstract, Single Table Inheritance) → `Book` (abstract) → subclasses, `User`, `ReadingList`, `ReadingProgress`, all enums (`Genre`, `Role`, `ReadingStatus`)
- **Singleton Pattern**: Implemented thread-safe double-checked locking `AppSettingsManager` (`config/AppSettingsManager.java`)
- **Repository Layer**: Defined all four JPA repositories with custom query methods

---

### 🟢 Shreyansh — Facade Pattern & Dashboard

- **Facade Pattern**: Designed and implemented `UserDashboardFacade` — a `@Component` that unifies four service calls (`UserService`, `ReadingListService`, `ReadingProgressService`, `ContentService`) into a single `getDashboardData(username)` method (`pattern/facade/UserDashboardFacade.java`)
- **Dashboard DTO**: Created `DashboardData` — the transfer object returned by the Facade, holding all aggregated dashboard data (`pattern/facade/DashboardData.java`)
- **DashboardController Refactoring**: Updated `DashboardController` to depend solely on the Facade abstraction instead of four separate service injections, applying the Dependency Inversion Principle
- **Thymeleaf UI**: Designed and built the shared base layout and all Thymeleaf HTML templates (dashboard, auth pages, reading list views)

---

### 🟡 Shubham — Factory Pattern & Content ,Admin Module

- **Factory Pattern**: Implemented `ContentFactory` — a static factory that uses a switch expression on `Genre` to instantiate the correct `Book` subclass (`FictionBook`, `ProgrammingBook`, or `ProductivityBook`) without exposing constructors to callers (`pattern/factory/ContentFactory.java`)
- **Book Subclasses**: Created the three concrete `Book` subclasses — `FictionBook`, `ProgrammingBook`, `ProductivityBook` — each mapped via JPA discriminator values
- **Content Module**: Built `ContentService` (uses `ContentFactory` internally) and `ContentController` (handles admin add/edit/delete for the content library, `content/form.html`, `content/list.html`)
- - **Admin Module**: Built the Admin dashboard, user management with per-user reading book aggregation (`AdminController.java`, `admin/users.html`, `admin/dashboard.html`)

---

### 🟠 Shrest — Builder Pattern & Reading List Module & Service layer foundation

- **Builder Pattern**: Implemented `ReadingListBuilder` — a fluent builder that constructs `ReadingList` objects step-by-step via `.name()`, `.owner()`, `.addContent()`, and `.build()` methods (`pattern/builder/ReadingListBuilder.java`)
- **Prototype Pattern**: Implemented `ProgressTemplate` — holds a static `ReadingProgress` template pre-configured to `NOT_STARTED / 0%` and exposes `cloneDefault()` via Java's `Cloneable` interface, used every time a user starts tracking a new book (`pattern/prototype/ProgressTemplate.java`)
- **Reading List Module**: Built `ReadingListService` (uses `ReadingListBuilder` internally) and `ReadingListController` — handling list creation, detail view, adding content, and deletion (`readinglist/list.html`, `readinglist/detail.html`, `readinglist/form.html`)
- - **Service Layer Foundation**: Implemented `UserService` and `ReadingProgressService` including progress percentage/page calculation logic


---

## 📄 License

This project was developed as part of an academic Object-Oriented Analysis and Design (OOAD) course mini-project.
