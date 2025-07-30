package org.YronJack.models;

public class Issue {
    private Integer issueId;
    private String issueDate;
    private String returnDate;
    private Student issueStudent;
    private Book issueBook;
    int counter = 0;

    public Issue(Integer issueId, String issueDate, String returnDate, Student issueStudent, Book issueBook) {
        this.issueId = counter;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.issueStudent = issueStudent;
        this.issueBook = issueBook;
        counter++;
    }

    // GETTERS AND SETTERS

    public Integer getIssueId() { return issueId; }
    public String getIssueDate() { return issueDate; }
    public String getReturnDate() { return returnDate; }
    public Student getIssueStudent() { return issueStudent; }
    public Book getIssueBook() { return issueBook; }

    public void setIssueId(Integer issueId) { this.issueId = issueId; }
    public void setIssueDate(String issueDate) { this.issueDate = issueDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    public void setIssueStudent(Student issueStudent) { this.issueStudent = issueStudent; }
    public void setIssueBook(Book issueBook) { this.issueBook = issueBook; }
}
