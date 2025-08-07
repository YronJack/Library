```markdown
# Library Management System

A Library Management System to help manage and acquire data about books used by students. This project is built in Java and provides a console-based application for managing books, students, and book issues.

## Features

- **Add New Books:** Store and manage information about books, including ISBN, title, category, quantity, and author.
- **Book Search:** Search books by title, author, or category.
- **Listings:** View all books, books by student, all issued books, books by category, never issued books, and all students.
- **Issue Management:** Track which student has borrowed which book, issue dates, and return dates.
- **Import/Export:** Load and save library data to CSV files.
- **Student Management:** Manage student records who borrow books.

## Menus Overview

- **Main Menu**
  - Add a book
  - Search books
  - Listings
  - Issues
  - Import/Export Library
  - Exit

- **Listings Menu**
  - List all books
  - List books by student
  - List all issued books
  - List books by category
  - List never issued books
  - List all students

- **Store Menu**
  - Load books
  - Delete CSV file
  - Save books to CSV
  - Back to Main Menu

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

## Usage

Upon running, you'll be presented with a main menu in the console. Use the menu options to add books, issue books, manage students, and view reports.

- Follow the prompts to enter book, student, and issue details.
- All data is persisted in CSV files for easy import/export.

## Project Structure

- `src/main/java/org/YronJack/`: Main Java source code.
- `models/`: Core data models (Book, Student, Issue, Author, Hub).
- `menus/`: Menu-driven UI logic.
- `store/`: Data persistence (loading/saving books, students, issues).
- `utils/`: Helper classes for listings and actions.

## Example Book Data (ISBNs)
```
9783161484100
9781861972712
9780131101630
9791234567896
```

## License

This project is currently unlicensed.

## Author

- Organization: [YronJack](https://github.com/YronJack)

---

*Library Management System for students and book tracking — built in Java.*
```
