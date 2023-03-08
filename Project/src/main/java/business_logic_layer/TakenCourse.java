package business_logic_layer;

public class TakenCourse {
    private Course course;
    private String courseCode;
    private String grade;
    private int gradeVal;
    private double credit;

    public TakenCourse(Course course, String grade) {
        this.course = course;
        this.grade = grade;
        this.courseCode = getCourseCode();
        this.gradeVal = setGradeVal(grade);
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
    public int setGradeVal(String letter) {
        int val = 0;
        if(letter.equals("A+")) val=9;
        else if(letter.equals("A")) val=8;
        else if(letter.equals("B+")) val=7;
        else if(letter.equals("B")) val=6;
        else if(letter.equals("C+")) val=5;
        else if(letter.equals("C")) val=4;
        else if(letter.equals("D+")) val=3;
        else if(letter.equals("D")) val=2;
        else if(letter.equals("E")) val=1;
        else val=0;
        return val;
    }
}
