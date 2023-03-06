package PersistenceLayer;

import BusinessLogicLayer.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static PersistenceLayer.mainScraper.getCourseList;
import static PersistenceLayer.mainScraper.getCourseListFromJSON;
import static org.junit.jupiter.api.Assertions.*;

class mainScraperTest {

    @Test
    void getCourseList_test01() {
        ArrayList<Course> fromJson = getCourseListFromJSON();
        ArrayList<Course> fromDB = getCourseList();

        assertArrayEquals(fromJson.toArray(), fromDB.toArray());
    }
}