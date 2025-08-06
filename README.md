


# IronLibrary Project Documentation
## Introduction
IronLibrary is a library management system designed to help users manage books, authors, students, and the issuing of books. This application includes functionalities such as searching and listing books, managing book issues, and handling data persistence.

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