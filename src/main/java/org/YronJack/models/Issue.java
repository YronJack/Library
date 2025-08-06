package org.YronJack.models;

import java.time.LocalDate;

public class Issue {
    private Integer issueId;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private Student issueStudent;
    private Book issueBook;

    public Issue(Integer issueId, LocalDate issueDate, LocalDate returnDate, Student issueStudent, Book issueBook) {
        this.issueId = issueId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.issueStudent = issueStudent;
        this.issueBook = issueBook;
    }

    public Issue() {

    }

    // GETTERS AND SETTERS

    public Integer getIssueId() { return issueId; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public Student getIssueStudent() { return issueStudent; }
    public Book getIssueBook() { return issueBook; }

    public void setIssueId(Integer issueId) { this.issueId = issueId; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public void setIssueStudent(Student issueStudent) { this.issueStudent = issueStudent; }
    public void setIssueBook(Book issueBook) { this.issueBook = issueBook; }
}
