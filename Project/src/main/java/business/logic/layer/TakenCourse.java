package business.logic.layer;

public class TakenCourse {
    private Course course;
    private String courseCode;
    private String grade;
    private int gradeVal;
    private double credit;

    // Class constructor
    public TakenCourse(Course course, String grade) {
        this.course = course;
        this.grade = grade;
        this.courseCode = getCourseCode();
        this.gradeVal = setGradeVal(grade);
        this.credit = Character.getNumericValue(getCourseCode().charAt(getCourseCode().length() - 4));
    }

    // Method returns course object
    public Course getCourse() {
        return course;
    }

    // Method returns course code
    public String getCourseCode() {
        return course.getCode();
    }

    // Method returns the letter grade received for the course
    public String getGrade() {
        return grade ;
    }

    // Method returns the grade in an integer form
    public int getGradeVal() {
        return gradeVal;
    }

    // Method returns the credit earned for the course
    public double getCredit() {
        return credit;
    }

    // Method converts the letter grade into integer one
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
