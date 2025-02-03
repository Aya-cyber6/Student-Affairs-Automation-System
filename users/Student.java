package termProject.users;

public class Student extends User {
    private String studentId;

    public Student(String studentId, String username) {
        super(username, null);  // Pass null for password if not required
        this.studentId = studentId;
    }

    @Override
    public String displayRole() {
        return "Student";
    }

    public String getStudentId() {
        return studentId;  // Return the studentId for comparison
    }
}
