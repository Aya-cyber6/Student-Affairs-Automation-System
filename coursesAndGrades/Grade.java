package termProject.coursesAndGrades;;

public class Grade {
    private String studentId;
    private String courseCode;
    private String examType;
    private double grade;

    public Grade(String studentId, String courseCode, String examType, double grade) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.examType = examType;
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getExamType() {
        return examType;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
