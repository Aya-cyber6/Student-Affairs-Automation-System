package termProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import termProject.coursesAndGrades.CourseManager;
import termProject.dashboards.BaseDashboard;
import termProject.dashboards.StudentDashboard;
import termProject.dashboards.SupervisorDashboard;
import termProject.dashboards.TeacherDashboard;
import termProject.users.Student;
import termProject.users.Supervisor;
import termProject.users.Teacher;
import termProject.users.User;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SignInFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField usernameField;
    private JComboBox<String> roleComboBox;

    // Static in-memory storage for users
    private static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SignInFrame frame = new SignInFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SignInFrame() {
        setTitle("Sign In");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(30, 40, 120, 20);
        contentPane.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(160, 40, 180, 25);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        JLabel lblRole = new JLabel("Select Role");
        lblRole.setBounds(30, 110, 120, 20);
        contentPane.add(lblRole);

        String[] roles = {"Supervisor", "Teacher", "Student"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(160, 110, 180, 25);
        contentPane.add(roleComboBox);

        JButton oKButton = new JButton("OK");
        oKButton.setBounds(150, 170, 100, 25);
        contentPane.add(oKButton);

        oKButton.addActionListener(e -> handleSignUp());
    }

    private void handleSignUp() {
        String username = usernameField.getText();
        String role = (String) roleComboBox.getSelectedItem();
        String passkey;

        BaseDashboard dashboard = null;
        
        // Create an instance of CourseManager
        CourseManager courseManager = new CourseManager();

        if ("Supervisor".equals(role)) {
            passkey = JOptionPane.showInputDialog(this, "Enter Passkey for Supervisor:", "Supervisor Passkey", JOptionPane.QUESTION_MESSAGE);
            if ("111".equals(passkey)) { // Passkey check for Supervisor
                users.put(username, new Supervisor(username, passkey));
                dashboard = new SupervisorDashboard(username, role);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid password for Supervisor!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if ("Teacher".equals(role)) {
            passkey = JOptionPane.showInputDialog(this, "Enter Passkey for Teacher:", "Teacher Passkey", JOptionPane.QUESTION_MESSAGE);
            if ("111".equals(passkey)) { // Passkey check for Teacher
                users.put(username, new Teacher(username, passkey));
                dashboard = new TeacherDashboard(username, role, courseManager);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid password for Teacher!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if ("Student".equals(role)) {
            String studentId = JOptionPane.showInputDialog(this, "Enter Student ID:", "Student Validation", JOptionPane.QUESTION_MESSAGE);
            if (studentId != null) { // Check if user did not cancel input
                if (users.containsKey(studentId)) {
                    JOptionPane.showMessageDialog(this, "Student ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    users.put(username, new Student(username, studentId));
                    dashboard = new StudentDashboard(username, role);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Student ID input canceled.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (dashboard != null) {
            dashboard.showDashboard();
            dispose(); // Close login frame
        } else {
            JOptionPane.showMessageDialog(this, "Unknown Role!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
