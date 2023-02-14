package courseSearch;

import courseStructures.Course;

import java.util.ArrayList;
import java.util.HashSet;

public class courseSearchMethods {
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

}
