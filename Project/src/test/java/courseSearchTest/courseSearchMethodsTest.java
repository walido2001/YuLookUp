package courseSearchTest;

import BusinessLogicLayer.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static PersistenceLayer.mainScraper.getCourseList;

class courseSearchMethodsTest {
    @Test
    public void courseSearch_test_01()
    {
        ArrayList<Course> courseList = getCourseList();

    }
}