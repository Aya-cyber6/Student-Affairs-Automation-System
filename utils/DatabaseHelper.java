package termProject.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import termProject.coursesAndGrades.Grade;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/school_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Khalifa3!";

    // Get a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Create the tables by executing the SQL script
    public static void createTables() {
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()) {

            // Read SQL commands from the file
            String createTablesSQL = new String(Files.readAllBytes(
                Paths.get("src/termProject/database/create_tables.sql")
            ));

            stmt.executeUpdate(createTablesSQL);
            System.out.println("Tables created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void insertStudent(String name, int id) throws SQLException {
        String query = "INSERT INTO students (student_id, student_name) VALUES (?, ?)";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.executeUpdate();
        }
    }

    public static void deleteStudent(int id) throws SQLException {
        String query = "DELETE FROM Students WHERE student_id = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static ResultSet getAllStudents() throws SQLException {
    	String query = "SELECT student_id, student_name FROM students";
        Connection connection = getConnection();
        return connection.createStatement().executeQuery(query);
    }

    public static ResultSet getPendingCourses() throws SQLException {
        String query = "SELECT * FROM student_courses WHERE course_status = 'Waiting for Approval'";
        Connection connection = getConnection();
        return connection.createStatement().executeQuery(query);
    }
    
    public static void insertStudentCourse(String username, String courseName, String course_status) throws SQLException {
        String query = "INSERT INTO student_courses (student_name, course_name, course_status) VALUES (?, ?, ?)";
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, courseName);
            ps.setString(3, course_status);
            ps.executeUpdate();
        }
    }

    public static void updateCourseStatus(String studentName, String courseName, String course_status) throws SQLException {
        String query = "UPDATE student_courses SET course_status = ? WHERE student_name = ? AND course_name = ?";
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, course_status);
            ps.setString(2, studentName);
            ps.setString(3, courseName);
            ps.executeUpdate();
        }
    }
    
    public static List<Grade> getGradesByStudent(String username) throws SQLException {
        List<Grade> gradesList = new ArrayList<>();
        String query = "SELECT course_code, exam_type, grade FROM grades WHERE student_username = ?";
        Connection connection = getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                gradesList.add(new Grade(username, rs.getString("course_code"), rs.getString("exam_type"), rs.getInt("grade")));
            }
        }
        return gradesList;
    }


    public static void main(String[] args) {
        try {
            createTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
