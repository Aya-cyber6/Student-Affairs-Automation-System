package termProject.users;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private static List<Student> students = new ArrayList<>();

    // Method to add a student
    public static void addStudent(Student student) {
        students.add(student);
    }

    // Method to get all students
    public static List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
}
