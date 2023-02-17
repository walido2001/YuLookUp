package courseSearchTest;

import BusinessLogicLayer.Course;
import BusinessLogicLayer.courseSearchMethods;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static PersistenceLayer.mainScraper.getCourseList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class courseSearchMethodsTest {

    @Test
    public void searchCourse_test_01()
    {
        ArrayList<Course> courseList = getCourseList();
        String input = "EECS";

        ArrayList<Course> courseSearchResult = courseSearchMethods.searchCourse(input, courseList);

        assertEquals(courseSearchResult.get(0).getCode(), "LE/EECS 1001 1.00");
        assertEquals(courseSearchResult.get(0).getName(), "Research Directions in Computing");

    }

    @Test
    public void searchCourse_test_02()
    {
        ArrayList<Course> courseList = getCourseList();
        String input = "eecs";

        ArrayList<Course> courseSearchResult = courseSearchMethods.searchCourse(input, courseList);

        assertEquals(courseSearchResult.get(0).getCode(), "LE/EECS 1001 1.00");
        assertEquals(courseSearchResult.get(0).getName(), "Research Directions in Computing");

    }

    @Test
    public void searchCourse_test_03()
    {
        ArrayList<Course> courseList = getCourseList();
        String input = "EECS 1011";
        String expectedResultCode = "LE/EECS 1011 3.00";
        String expectedResultName = "Computational Thinking through Mechatronics";

        ArrayList<Course> courseSearchResult = courseSearchMethods.searchCourse(input, courseList);

        assertEquals(courseSearchResult.get(0).getCode(), expectedResultCode);
        assertEquals(courseSearchResult.get(0).getName(), expectedResultName);

    }

    @Test
    public void searchCourse_test_04()
    {
        ArrayList<Course> courseList = getCourseList();
        String input = "/";

        ArrayList<Course> courseSearchResult = courseSearchMethods.searchCourse(input, courseList);

        assertTrue(courseSearchResult.size() == courseList.size());
    }
}