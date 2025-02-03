package termProject.dashboards;

import termProject.coursesAndGrades.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ManageCoursesFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    // In-memory course data
    private List<Course> courseList;

    public ManageCoursesFrame() {
        // Sample data for demonstration
        courseList = new ArrayList<>();
        courseList.add(new Course("John Doe", "Math 101", "Pending"));
        courseList.add(new Course("Jane Smith", "CS 101", "Pending"));

        setTitle("Manage Student Courses");
        setLayout(new BorderLayout());
        setSize(600, 400);

        // Prepare table data from in-memory courseList
        String[] columnNames = {"Student", "Course", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Course course : courseList) {
            model.addRow(new Object[]{course.getStudentName(), course.getCourseName(), course.getCourseStatus()});
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane, BorderLayout.CENTER);

        // Approve/Disapprove buttons
        JButton approveButton = new JButton("Approve");
        JButton disapproveButton = new JButton("Disapprove");

        approveButton.addActionListener(e -> updateCourseStatus(table, "Approved"));
        disapproveButton.addActionListener(e -> updateCourseStatus(table, "Disapproved"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(approveButton);
        buttonPanel.add(disapproveButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateCourseStatus(JTable table, String newStatus) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String studentName = table.getValueAt(selectedRow, 0).toString();
        String courseName = table.getValueAt(selectedRow, 1).toString();

        // Find and update the course status in the in-memory list
        for (Course course : courseList) {
            if (course.getStudentName().equals(studentName) && course.getCourseName().equals(courseName)) {
                course.setCourseStatus(newStatus);
                JOptionPane.showMessageDialog(this, "Course status updated to " + newStatus + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                ((DefaultTableModel) table.getModel()).setValueAt(newStatus, selectedRow, 2);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Course not found!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
