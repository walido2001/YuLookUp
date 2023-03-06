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
        return (prerequisites == null) ? new ArrayList<>() : this.prerequisites;
    }

    public ArrayList<Course> getCoursePrerequisites()
    {
        if (this.prerequisites == null || this.prerequisites.size() == 0)
        {
            return new ArrayList<>();
        }

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

    public String getSubjectType()
    {
        // Format: FACULTY/DEP CODE CREDITSIZE
        String[] seperatedString = this.code.split(" ");
        if(seperatedString.length > 0)
        {
            // Format: FACULTY/DEP
            String[] facultyDep = seperatedString[0].split("/");
            return facultyDep[1];
        }
        return "";
    }

    public String getFacultyType()
    {
        // Format: FACULTY/DEP CODE CREDITSIZE
        String[] seperatedString = this.code.split(" ");
        if(seperatedString != null && seperatedString.length > 0)
        {
            // Format: FACULTY/DEP
            String[] facultyDep = seperatedString[0].split("/");
            return facultyDep[0];
        }
        return "";
    }

    public int getLevel()
    {
        // Format: FACULTY/DEP CODE CREDITSIZE
        String[] seperatedString = this.code.split(" ");
        return (seperatedString != null && seperatedString.length == 3) ? Integer.parseInt(String.valueOf(seperatedString[1].charAt(0))) : -1;
    }

    public int getCreditAmount()
    {
        // Format: FACULTY/DEP CODE CREDITSIZE
        String[] seperatedString = this.code.split(" ");
        return (seperatedString != null && seperatedString.length == 3) ? Integer.parseInt(String.valueOf(seperatedString[2].charAt(0))) : -1;
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
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;

        Course c = (Course) obj;

        if(this.getName() == null) {
            if(c.getName() != null)
                return false;
        }
        else if(!this.getName().equals(c.getName()))
            return false;

        if(this.getDescription() == null) {
            if(c.getDescription() != null)
                return false;
        }
        else if(!this.getDescription().equals(c.getDescription()))
            return false;

        if(this.getCode() == null) {
            if(c.getCode() != null)
                return false;
        }
        else if(!this.getCode().equals(c.getCode()))
            return false;

        if(this.getCoursePrerequisites() == null) {
            if(c.getCoursePrerequisites() != null)
                return false;
        }
        else if(!this.getCoursePrerequisites().equals(c.getCoursePrerequisites()))
            return false;

        return true;
    }

}
