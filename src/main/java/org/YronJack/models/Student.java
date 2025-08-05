package org.YronJack.models;

public class Student {
    private String usn;
    private String name;

    public Student(String usn, String name) {
        this.usn = usn;
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }

    public String getUsn() { return usn; }
    public String getName() { return name; }

    public void setUsn(String usn) { this.usn = usn; }
    public void setName(String name) { this.name = name; }
}
