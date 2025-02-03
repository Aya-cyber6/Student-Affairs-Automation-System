package termProject.dashboards;

import termProject.coursesAndGrades.Grade;
import termProject.interfaces.DashboardManagment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDashboard extends BaseDashboard implements DashboardManagment {
    private static final long serialVersionUID = 1L;

    // Static in-memory storage for grades and courses
    private static final List<Grade> gradesList = new ArrayList<>();
    private static final List<String> selectedCourses = new ArrayList<>();

    public StudentDashboard(String username, String role) {
        super(username, role);
        initialize();
    }

    @Override
    public void initialize() {
        setTitle("Student Dashboard");
        getContentPane().setLayout(new BorderLayout());

        // Header Label
        JLabel lblWelcome = new JLabel("Welcome, " + username);
        lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblWelcome, BorderLayout.NORTH);

        // Main panel with buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 10, 10));

        JButton btnViewGrades = new JButton("View Grades");
        JButton btnSelectCourses = new JButton("Select Courses");
        JButton btnChangePassword = new JButton("Change Password");
        JButton btnViewProfile = new JButton("View Profile");
        JButton btnLogout = new JButton("Logout");

        buttonPanel.add(btnViewGrades);
        buttonPanel.add(btnSelectCourses);
        buttonPanel.add(btnChangePassword);
        buttonPanel.add(btnViewProfile);
        buttonPanel.add(btnLogout);

        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        // Action Listeners for buttons
        btnViewGrades.addActionListener(e -> viewGrades());
        btnSelectCourses.addActionListener(e -> chooseCourse());
        btnChangePassword.addActionListener(e -> changePassword());
        btnViewProfile.addActionListener(e -> viewProfile(username, role));
        btnLogout.addActionListener(e -> logout(this));
    }

    // Method to view grades for the logged-in student
    private void viewGrades() {
        // Create a table model with column names
        String[] columnNames = {"Course Code", "Exam Type", "Grade"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Populate the table model with data from gradesList
        for (Grade grade : gradesList) {
            model.addRow(new Object[]{grade.getCourseCode(), grade.getExamType(), grade.getGrade()});
        }

        // Create a JTable and set the model
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        // Show the table in a dialog
        JOptionPane.showMessageDialog(this, scrollPane, "Your Grades", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to choose courses
    private void chooseCourse() {
        String courseName = JOptionPane.showInputDialog(this, "Enter Course Name:", "Select Course", JOptionPane.QUESTION_MESSAGE);
        if (courseName != null && !courseName.isEmpty()) {
            if (selectedCourses.contains(courseName)) {
                JOptionPane.showMessageDialog(this, "Course is already selected!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                selectedCourses.add(courseName);
                JOptionPane.showMessageDialog(this, "Course added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
