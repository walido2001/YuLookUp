package BusinessLogicLayer;

public class TakenCourse {
    private Course course;
    private String courseCode;
    private String grade;
    private int gradeVal;
    private double credit;

    public TakenCourse(Course course, String grade, int gradeVal) {
        this.course = course;
        this.grade = grade;
        this.courseCode = getCourseCode();
        this.gradeVal = gradeVal;
        this.credit = Character.getNumericValue(getCourseCode().charAt(getCourseCode().length() - 4));
    }

    public Course getCourse() {
        return course;
    }
    public String getCourseCode() {
        return course.getCode();
    }
    public String getGrade() {
        return grade ;
    }
    public int getGradeVal() {
        return gradeVal;
    }
    public double getCredit() {
        return credit;
    }
}
