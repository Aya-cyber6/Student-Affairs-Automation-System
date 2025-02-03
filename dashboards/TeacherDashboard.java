package termProject.dashboards;

import termProject.coursesAndGrades.CourseManager;
import termProject.interfaces.DashboardManagment;
import termProject.users.Student;
import termProject.users.StudentManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TeacherDashboard extends BaseDashboard implements DashboardManagment {
    private static final long serialVersionUID = 1L;
    private CourseManager courseManager;

    // Constructor accepting a CourseManager instance
    public TeacherDashboard(String username, String role, CourseManager courseManager) {
        super(username, role);
        this.courseManager = courseManager;
        initialize();
    }

    @Override
    public void initialize() {
        setTitle(APP_NAME + " - Teacher Dashboard");
        getContentPane().setLayout(new GridLayout(8, 1)); // Adjusted grid layout for additional buttons

        JLabel label = new JLabel("Welcome, Mr./Ms.: " + username);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(label);

        JButton viewProfileButton = new JButton("View Profile");
        JButton changePasswordButton = new JButton("Change Password");
        JButton addGradeButton = new JButton("Assign Grades");
        JButton viewStudentsGradesButton = new JButton("View Students and Grades");
        JButton logoutButton = new JButton("Logout");

        // Add buttons to the dashboard
        getContentPane().add(viewProfileButton);
        getContentPane().add(changePasswordButton);
        getContentPane().add(addGradeButton);
        getContentPane().add(viewStudentsGradesButton);
        getContentPane().add(logoutButton);

        // Add action listeners to buttons
        viewProfileButton.addActionListener(e -> viewProfile(username, role));
        changePasswordButton.addActionListener(e -> changePassword());
        addGradeButton.addActionListener(e -> openGradeAssignmentFrame());
        viewStudentsGradesButton.addActionListener(e -> viewStudentsGrades());
        logoutButton.addActionListener(e -> logout(this));
    }

    // Method to open the GradeAssignmentFrame for assigning grades
    private void openGradeAssignmentFrame() {
        GradeAssignmentFrame gradeAssignmentFrame = new GradeAssignmentFrame(courseManager);
        gradeAssignmentFrame.setVisible(true);
    }

    // Method to view all students and their grades
    public void viewStudentsGrades() {
        try {
            // Retrieve the shared student list from the StudentManager
            List<Student> studentsList = StudentManager.getAllStudents();

            if (studentsList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No students available to display.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Create a table model with column names
            String[] columnNames = {"Student ID", "Name"};

            // Create a table model
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            // Populate the table model with data from the studentsList
            for (Student student : studentsList) {
                // Assuming `getStudentId()` returns the student's ID and `getUsername()` returns their name
                model.addRow(new Object[]{student.getStudentId(), student.getUsername()});
            }

            // Create a JTable and set the model
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);

            // Show the table in a dialog
            JOptionPane.showMessageDialog(this, scrollPane, "Students List", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to retrieve student grades.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

public void viewStudents() {
    List<Student> students = StudentManager.getAllStudents();  // Access the shared student list
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
