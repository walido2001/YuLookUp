package business.logic.layer;

import java.util.ArrayList;

public class UserProfile {
    private String name;
    private String studentID;
    private String major;
    private ArrayList<TakenCourse> courses;

    public UserProfile()
    {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public ArrayList<TakenCourse> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<TakenCourse> courses) {
        this.courses = courses;
    }

}
