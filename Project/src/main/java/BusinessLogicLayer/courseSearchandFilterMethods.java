package BusinessLogicLayer;

import java.util.ArrayList;
import java.util.HashSet;

public class courseSearchandFilterMethods {
    public static ArrayList<Course> searchCourse(String search, ArrayList<Course> directory)
    {
        ArrayList<Course> returnable = new ArrayList<>();

        HashSet<Course> checkedCourses = new HashSet<>();

        for (int i = search.length(); i >= search.length()/3; i--) {
            String curr = search.substring(0, i).toLowerCase();
            for (Course course: directory)
            {
                String name = course.getName();
                String code = course.getCode();

                if (!checkedCourses.contains(course))
                {
                    if (name.toLowerCase().contains(curr) || code.toLowerCase().contains(curr))
                    {
                        returnable.add(course);
                        checkedCourses.add(course);
                    }
                }
            }
        }

        return returnable;
    }

    public static ArrayList<Course> filterDepartment(ArrayList<Course> currentResult, String dep)
    {
        ArrayList<Course> returnable = new ArrayList<>();

        for (Course course : currentResult)
        {
            if (course.getFacultyType().equals(dep))
            {
                returnable.add(course);
            }
        }

        return returnable;
    }

    public static ArrayList<Course> filterSubject(ArrayList<Course> currentResult, String subject)
    {
        ArrayList<Course> returnable = new ArrayList<>();

        for (Course course : currentResult)
        {
            if (course.getSubjectType().equals(subject))
            {
                returnable.add(course);
            }
        }

        return returnable;
    }

    public static ArrayList<Course> filterYearLevel(ArrayList<Course> currentResult, int yearLevel)
    {
        ArrayList<Course> returnable = new ArrayList<>();

        for (Course course : currentResult)
        {
            if (course.getLevel() == yearLevel)
            {
                returnable.add(course);
            }
        }

        return returnable;
    }

    public static ArrayList<Course> filterCreditAmount(ArrayList<Course> currentResult, int creditAmount)
    {
        ArrayList<Course> returnable = new ArrayList<>();

        for (Course course : currentResult)
        {
            if (course.getCreditAmount() == creditAmount)
            {
                returnable.add(course);
            }
        }

        return returnable;
    }
}
