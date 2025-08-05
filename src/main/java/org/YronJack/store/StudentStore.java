package org.YronJack.store;

import org.YronJack.models.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentStore {

    private static final String FILE_NAME = "data/students.csv";

    public StudentStore() {
        ensureFileExists();
    }

    private static void ensureFileExists() {
        File file = new File(FILE_NAME);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println("usn,name");
            } catch (IOException e) {
                System.err.println("❌ Error creating students.csv: " + e.getMessage());
            }
        }
    }

    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return students;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String usn = parts[0].trim();
                    String name = parts[1].trim();
                    students.add(new Student(usn, name));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void saveStudent(Student student) {
        ensureFileExists();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(String.format("%s,%s", student.getUsn(), student.getName()));
            bw.newLine();
        } catch (IOException e) {
            System.err.println("❌ Error saving student: " + e.getMessage());
        }
    }
}

