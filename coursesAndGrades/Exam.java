package termProject.coursesAndGrades;

import java.util.Date;

public class Exam {
    private String courseCode;
    private String examType;
    private Date examDate;

    public Exam(String courseCode, String examType, Date examDate) {
        this.courseCode = courseCode;
        this.examType = examType;
        this.examDate = examDate;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getExamType() {
        return examType;
    }

    public Date getExamDate() {
        return examDate;
    }
}
