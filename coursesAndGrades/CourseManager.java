package termProject.coursesAndGrades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseManager {
    private Map<String, Course> courseCatalog;
    private Map<String, Grade> gradeBook;

    public CourseManager() {
        this.courseCatalog = new HashMap<>();
        this.gradeBook = new HashMap<>();
    }

    public void addCourse(Course course) {
        courseCatalog.put(course.getCourseName(), course);
    }

    public void assignGrade(String studentId, String courseCode, String examType, double grade) {
        Grade newGrade = new Grade(studentId, courseCode, examType, grade);
        gradeBook.put(studentId + courseCode + examType, newGrade);
    }

    public Grade getGrade(String studentId, String courseCode, String examType) {
        return gradeBook.get(studentId + courseCode + examType);
    }

    public void approveCourses(String studentId) {
        // Logic to approve courses based on prerequisites or other rules
        System.out.println("Approving courses for student: " + studentId);
    }

    // Removed the database dependency and replaced it with in-memory data
    public List<Grade> getGradesByStudent(String studentId) {
        List<Grade> gradesList = new ArrayList<>();
        
        // Check the gradeBook for the student grades
        for (Grade grade : gradeBook.values()) {
            if (grade.getStudentId().equals(studentId)) {
                gradesList.add(grade);
            }
        }
        
        return gradesList;
    }

    // Example of getting all grades, now using in-memory data
    public List<Grade> getAllGrades() {
        return new ArrayList<>(gradeBook.values());
    }

    // Get all courses in the course catalog
    public List<Course> getCourseList() {
        return new ArrayList<>(courseCatalog.values());
    }

    // Get a list of all enrolled students
    public List<String> getEnrolledStudents() {
        List<String> enrolledStudents = new ArrayList<>();
        
        // Iterate through all courses and gather unique student names
        for (Course course : courseCatalog.values()) {
            for (String studentId : course.getEnrolledStudents()) {
                if (!enrolledStudents.contains(studentId)) {
                    enrolledStudents.add(studentId);
                }
            }
        }
        
        return enrolledStudents;
    }
}
