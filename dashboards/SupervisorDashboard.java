package termProject.dashboards;

import termProject.interfaces.DashboardManagment;
import termProject.users.Student;
import termProject.users.StudentManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SupervisorDashboard extends BaseDashboard implements DashboardManagment {
    private static final long serialVersionUID = 1L;

    // List to store student data
    private List<Student> students = new ArrayList<>();

    public SupervisorDashboard(String username, String role) {
        super(username, role);
        initialize();
    }

    public void initialize() {
        setTitle(APP_NAME + " - Supervisor Dashboard");
        getContentPane().setLayout(new GridLayout(8, 1));  // Adjusted grid layout for additional buttons

        JLabel label = new JLabel("Welcome, : " + username);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(label);

        JButton viewProfileButton = new JButton("View Profile");
        JButton addStudentButton = new JButton("Add Student");
        JButton deleteStudentButton = new JButton("Delete Student");
        JButton viewStudentsButton = new JButton("View Students");
        JButton confirmCoursesButton = new JButton("Confirm Student Choices");
        JButton logoutButton = new JButton("Logout");

        // Add buttons to the dashboard
        getContentPane().add(viewProfileButton);
        getContentPane().add(addStudentButton);
        getContentPane().add(deleteStudentButton);
        getContentPane().add(viewStudentsButton);
        getContentPane().add(confirmCoursesButton);
        getContentPane().add(logoutButton);

        // Add action listeners to buttons
        viewProfileButton.addActionListener(e -> viewProfile(username, role));
        addStudentButton.addActionListener(e -> insertStudent());
        deleteStudentButton.addActionListener(e -> deleteStudent());
        viewStudentsButton.addActionListener(e -> viewStudents());
        confirmCoursesButton.addActionListener(e -> new ManageCoursesFrame().setVisible(true));
        logoutButton.addActionListener(e -> logout(this));
    }

    // Insert student functionality
    public void insertStudent() {
        String studentName = JOptionPane.showInputDialog(this, "Enter Student Name:", "Add Student", JOptionPane.QUESTION_MESSAGE);
        String studentId = JOptionPane.showInputDialog(this, "Enter Student ID:", "Add Student", JOptionPane.QUESTION_MESSAGE);

        if (studentName != null && studentId != null) {
            Student newStudent = new Student(studentId, studentName);
            StudentManager.addStudent(newStudent);  // Add the student to the shared list
            JOptionPane.showMessageDialog(this, "Student added successfully!", "Add Student", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public void deleteStudent() {
        String studentId = JOptionPane.showInputDialog(this, "Enter Student ID to Delete:", "Delete Student", JOptionPane.QUESTION_MESSAGE);

        if (studentId != null) {
            boolean found = false;
            
            // Using an Iterator to safely remove while iterating
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                if (student.getStudentId().equals(studentId)) {
                    students.remove(i);
                    JOptionPane.showMessageDialog(this, "Student deleted successfully!", "Delete Student", JOptionPane.INFORMATION_MESSAGE);
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                JOptionPane.showMessageDialog(this, "Student ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    // View all students in a table format
    public void viewStudents() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No students available to display.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] columnNames = {"ID", "Name"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Student student : students) {
            model.addRow(new Object[]{student.getStudentId(), student.getUsername()});
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JOptionPane.showMessageDialog(this, scrollPane, "Students List", JOptionPane.INFORMATION_MESSAGE);
    }
}
