package termProject.coursesAndGrades;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String studentName;
    private String courseName;
    private String courseStatus;
    
    // List to store students enrolled in the course
    private List<String> enrolledStudents = new ArrayList<>();

    // Add a CourseManager instance to access course list
    private static CourseManager courseManager = new CourseManager(); // Assuming it's globally accessible

    public Course(String studentName, String courseName, String courseStatus) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.courseStatus = courseStatus;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    // Adds a student to the course
    public void addStudent(String studentId) {
        if (!enrolledStudents.contains(studentId)) {
            enrolledStudents.add(studentId);
            System.out.println("Student " + studentId + " enrolled in " + courseName);
        } else {
            System.out.println("Student " + studentId + " is already enrolled in " + courseName);
        }
    }

    // Removes a student from the course
    public void removeStudent(String studentId) {
        if (enrolledStudents.contains(studentId)) {
            enrolledStudents.remove(studentId);
            System.out.println("Student " + studentId + " removed from " + courseName);
        } else {
            System.out.println("Student " + studentId + " is not enrolled in " + courseName);
        }
    }

    // Returns a list of enrolled students' IDs
    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }
    
    // Returns all courses (now correctly referencing CourseManager)
    public static List<Course> getCourseList() {
        return courseManager.getCourseList();  // Accessing CourseManager's method
    }
}
