package BusinessLogicLayer;

import java.util.ArrayList;
import java.util.Locale;

import static PersistenceLayer.mainScraper.getCourseList;

public class Course {
    private String code;
    private String name;
    private String description;
    private ArrayList<String> prerequisites;

    public Course(String code, String name, String description)
    {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Course(String code, String name, String description, ArrayList<String> prerequisites)
    {
        this.code = code;
        this.name = name;
        this.description = description;
        this.prerequisites = prerequisites;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionWithoutPrereq()
    {
        int PrereqIndex = this.description.toLowerCase(Locale.ROOT).lastIndexOf("prerequisite");

        return (PrereqIndex != -1) ? this.description.substring(0, PrereqIndex) : this.description;
    }

    public ArrayList<String> getPrerequisites()
    {
        return this.prerequisites;
    }

    public ArrayList<Course> getCoursePrerequisites()
    {
        ArrayList<Course> returnable = new ArrayList<>();
        ArrayList<Course> courseList = getCourseList();
        for (Course course : courseList)
        {
            if (this.prerequisites.contains(course.getCode()))
            {
                returnable.add(course);
            }
        }

        return returnable;
    }

    public String toString()
    {
        return this.code + " | "+ this.name;
    }
//    public ArrayList<Course> getPrerequisites() {
//        return prerequisites;
//    }

//    public void addPrerequisite(Course prereq)
//    {
//        if (!prerequisites.contains(prereq))
//        {
//            this.prerequisites.add(prereq);
//        }
//    }

//    public void setPrerequisites(ArrayList<Course> prerequisites) {
//        this.prerequisites = prerequisites;
//    }




}
