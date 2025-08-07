
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

- **Add New Books:** Store and manage information about books, including ISBN, title, category, quantity, and author.
- **Book Search:** Search books by title, author, or category.
- **Listings & Reports:** View all books, books by student, issued/never issued books, books by category, and all students.
- **Issue Management:** Track which student has borrowed which book, issue dates, and return dates.
- **Import/Export:** Load and save library data to CSV files.
- **Student Management:** Manage student records who borrow books.

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

## Project Structure

```plaintext
src/                # Java source code
  Main.java         # Main entry point
  Book.java         # Book model
  Student.java      # Student model
  Issue.java        # Issue model
  ...               # Other classes

data/               # CSV data files
  books.csv
  students.csv
  issues.csv

assets/screenshots/ # Screenshots for documentation

bin/                # Compiled .class files (ignored in git)
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



