
# Library Management System

A console-based Java application to help schools and organizations manage books and student borrowing efficiently. This project solves the problem of manual book tracking, allowing you to add, issue, search, and report on books and students, with persistent data storage in CSV files.




[![Java](https://img.shields.io/badge/Java-8%2B-blue)](https://www.java.com/)
![Last Commit](https://img.shields.io/github/last-commit/YronJack/Library)
![Issues](https://img.shields.io/github/issues/YronJack/Library)

---

## Table of Contents

- [Features](#features)
- [Demo](#demo)
- [Getting Started](#getting-started)
- [Usage Examples](#usage-examples)
- [Data Formats](#data-formats)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---
## Features
### Main Classes:

 - Author: Represents authors with relevant details.
- Hub: Central class that orchestrates the main operations of the library.
- Book: Represents books with title, author, and category information.
- Student: Represents students with unique identifiers.
- Issue: Manages the issuance of books to students.
- BookStore: Manages the collection of books.
- IssueStore: Manages the collection of issued books.
- StudentStore: Manages the collection of students.
### Interactive Menu:

- Developed using console-based navigation to handle user interactions.
- Options include: Add/Search/List books, Manage book issues, etc.
- Robust exception handling for user inputs.
- Search functionality to find books by title, author, or category.
- Listing functionality to display all books, issued books, and students.
- Issue management to allow students to borrow and return books.

### Data Persistence:

- Current implementation uses CSV files for data storage and retrieval.
- Data is stored in separate files for books, students, and issues.
- Each file is structured to allow easy reading and writing of data.
- Example file structure:
  - `books.csv`: Contains book details (title, author, category).
  - `students.csv`: Contains student details (name, ID).
  - `issues.csv`: Contains issue records (book ID, student ID, issue date, return date).
- Data is loaded into memory at application startup and saved back to files on exit.
- Future improvement: Implement a more robust data storage solution as a SQL database using JDBC/JPA.

### Unit Testing:

- Comprehensive tests using JUnit for critical methods.
- Covers positive, negative scenarios, and edge cases.

### Error Handling:

Handles invalid inputs gracefully with user-friendly feedback messages.
- Additional Functionalities:

Import/Export library data.
Scalable design to introduce more features.
## Project Structure

The project follows a standard Maven directory layout:

```
src/
  main/
    java/
      org/
        YronJack/
          Main.java
          enums/
            Category.java
          menus/
            IssueMenu.java
            ListMenu.java
            MainMenu.java
            SearchMenu.java
            StoreMenu.java
          models/
            Author.java
            Book.java
            Hub.java
            Issue.java
            Student.java
          store/
            BookStore.java
            IssueStore.java
            StudentStore.java
          utils/
            CreateBookAction.java
            IssueActions.java
            ListingActions.java
            SearchActions.java
  test/
    java/
      test/
        BookStoreTest.java
        IssueStoreTest.java
        SearchActionsTest.java
        StudentStoreTest.java
```

- **models/**: Contains core data models such as `Book`, `Author`, `Student`, `Issue`, and `Hub`.
- **store/**: Contains classes responsible for managing collections of books, students, and issues.
- **menus/**: Contains menu classes for user interaction.
- **utils/**: Contains utility classes for actions like creating books, issuing, listing, and searching.
- **enums/**: Contains enumerations, e.g., `Category`.
- **test/**: Contains unit tests for main components.



## Data Diagram

**Entities and Relationships:**

- **Book**: Represents a book, associated with one or more `Author` and a `Category`.
- **Author**: Represents an author, can be linked to multiple books.
- **Student**: Represents a library user who can borrow books.
- **Issue**: Represents the act of a student borrowing a book.
- **Hub**: Central class for managing the main operations.

```
[Author]---<writes>---[Book]---<issued as>---[Issue]---<borrowed by>---[Student]
```

## Data Models

- **Book**: Contains fields such as title, author(s), category, and unique identifier.
- **Author**: Contains name and identifier.
- **Student**: Contains name, student ID, and contact information.
- **Issue**: Contains references to `Book` and `Student`, and issue/return dates.

## Usage Examples

**Add a Book**
```
Main Menu > Add a book
Enter ISBN: 9780131101630
Enter Title: The C Programming Language
...
Book added successfully!
```

**Issue a Book**
```
Main Menu > Issues > Issue a book
Enter Student ID: 12345
Enter Book ISBN: 9780131101630
Issue date: 2025-08-07
Book issued!
```

**List Never Issued Books**
```
Listings Menu > List never issued books
[Displays all books that have never been borrowed]
```

---

## Data Formats

**Books CSV**
```
ISBN,Title,Category,Quantity,Author
9780131101630,The C Programming Language,Programming,2,Kernighan & Ritchie
```

**Students CSV**
```
StudentID,Name,Email
12345,Jane Doe,jane@example.com
```

**Issues CSV**
```
StudentID,ISBN,IssueDate,ReturnDate
12345,9780131101630,2025-08-07,2025-09-01
```

---

## Getting Started

### Prerequisites

- Java 8 or higher
- Git

### Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/YronJack/Library.git
   ```
2. **Navigate to the project directory:**
   ```sh
   cd Library
   ```
3. **Compile the project:**
   ```sh
   javac -d bin src/main/java/org/YronJack/**/*.java
   ```
4. **Run the application:**
   ```sh
   java -cp bin org.YronJack.Main
   ```

---





## Contributing

Contributions are welcome! Please [open an issue](https://github.com/YronJack/Library/issues) or submit a pull request.

1. Fork the repo
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Open a pull request

---



*Library Management System for students and book tracking — built in Java.*



