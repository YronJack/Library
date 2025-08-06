
# Library

## Project Objective

The **Library** project is a Java-based application designed to manage a library system. It provides functionalities for managing books, authors, students, and book issues, supporting typical library operations such as searching, listing, issuing, and storing books.

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

## Main Features

- Book, author, and student management
- Book issue and return operations
- Search and listing functionalities
- Menu-driven user interface

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

## Installation and Setup

1. **Clone the repository:**
   ```
   git clone <repository-url>
   ```

2. **Navigate to the project directory:**
   ```
   cd Library
   ```

3. **Build the project using Maven:**
   ```
   mvn clean install
   ```

4. **Run the application:**
   ```
   mvn exec:java -Dexec.mainClass="org.YronJack.Main"
   ```

5. **Run tests:**
   ```
   mvn test
   ```

## Requirements

- Java 17 or higher
- Maven 3.6 or higher

---

For further details, refer to the source code and comments within each class.