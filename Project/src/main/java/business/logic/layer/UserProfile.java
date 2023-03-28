package business.logic.layer;

import java.util.ArrayList;

public class UserProfile {
    private String name;
    private String studentID;
    private String major;
    private ArrayList<TakenCourse> courses;
    private static UserProfile instance;

    public static UserProfile getInstanceOfUserProfile()
    {
      if (instance == null)
      {
          instance = new UserProfile();
          return instance;
      }
        return instance;
    }

    private UserProfile()
    {
        this.name="";
        this.studentID="";
        this.major="";
    }

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

    public ArrayList<Course> getRegularCourses()
    {
        ArrayList<Course> returnable = new ArrayList<>();
        for (TakenCourse taken: this.courses)
        {
            returnable.add(taken.getCourse());
        }
        return returnable;
    }

    public void setCourses(ArrayList<TakenCourse> courses) {
        this.courses = courses;
    }

}
