package org.YronJack.models;

import java.util.UUID;

public class Student {
    private String usn;
    private String name;

    // Constructor

    public Student(String name) {
        this.name = name;
        this.usn = generateUSN();
    }

    public Student(String usn, String name) {
        this.usn = usn;
        this.name = name;
    }

    private String generateUSN() {
        return "USN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Getters y setters

    public String getUsn() { return usn; }
    public String getName() { return name; }
    public void setUsn(String usn) { this.usn = usn; }
    public void setName(String name) { this.name = name; }
}
