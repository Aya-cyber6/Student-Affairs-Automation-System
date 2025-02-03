package termProject.dashboards;

import termProject.coursesAndGrades.CourseManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class GradeAssignmentFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private CourseManager courseManager;
    private JTable studentTable;

    public GradeAssignmentFrame(CourseManager courseManager) {
        this.courseManager = courseManager;
        initialize();
    }

    private void initialize() {
        setTitle("Assign Grades to Students");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table for displaying students
        String[] columnNames = {"Student ID", "Student Name"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Create some random students for demo purposes
        List<String[]> dummyStudents = Arrays.asList(
            new String[]{"S001", "John Doe"},
            new String[]{"S002", "Jane Smith"},
            new String[]{"S003", "Alice Johnson"},
            new String[]{"S004", "Bob Brown"}
        );

        // Populate the table with dummy students
        for (String[] student : dummyStudents) {
            model.addRow(student);
        }

        studentTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        // Input fields for course name, exam type, and grade
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JLabel courseLabel = new JLabel("Course Name:");
        JTextField courseNameField = new JTextField();
        JLabel examTypeLabel = new JLabel("Exam Type:");
        JTextField examTypeField = new JTextField();
        JLabel gradeLabel = new JLabel("Grade:");
        JTextField gradeField = new JTextField();

        inputPanel.add(courseLabel);
        inputPanel.add(courseNameField);
        inputPanel.add(examTypeLabel);
        inputPanel.add(examTypeField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);

        add(inputPanel, BorderLayout.NORTH);

        // Button for submitting grade
        JButton assignGradeButton = new JButton("Assign Grade");
        assignGradeButton.addActionListener(e -> assignGrade(courseNameField.getText(), examTypeField.getText(), gradeField.getText()));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(assignGradeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void assignGrade(String courseName, String examType, String gradeStr) {
        try {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a student to assign a grade.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String studentId = studentTable.getValueAt(selectedRow, 0).toString();

            // Parse the grade
            double grade = Double.parseDouble(gradeStr);

            // Call CourseManager to assign the grade
            courseManager.assignGrade(studentId, courseName, examType, grade);

            // Display success message
            JOptionPane.showMessageDialog(this, "Grade " + grade + " successfully assigned to student: " + studentId, "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric grade.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to assign grade.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
