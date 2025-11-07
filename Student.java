import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String name;
    private List<CourseGrade> grades;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.grades = new ArrayList<>();
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<CourseGrade> getGrades() {
        return grades;
    }

    public void addGrade(String courseCode, String grade, int credits) {
        grades.add(new CourseGrade(courseCode, grade, credits));
    }

    @Override
    public String toString() {
        return "ID: " + studentId + ", Name: " + name;
    }
}

class CourseGrade {
    String courseCode;
    String grade;
    int credits;

    public CourseGrade(String courseCode, String grade, int credits) {
        this.courseCode = courseCode;
        this.grade = grade;
        this.credits = credits;
    }
}